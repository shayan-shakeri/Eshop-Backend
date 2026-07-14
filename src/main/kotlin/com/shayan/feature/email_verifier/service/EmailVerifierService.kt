package com.shayan.feature.email_verifier.service

import com.shayan.core.exception.InvalidCredentials
import com.shayan.core.security.hasher.Sha256Hasher
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.email_verifier.model.EmailVerifier
import com.shayan.feature.email_verifier.dto.SendEmail
import com.shayan.feature.email_verifier.dto.VerifyEmail

import com.shayan.feature.email_verifier.repository.EmailVerifierRepository
import com.shayan.feature.email_verifier.constants.EmailVerifierConst

import core.database.dbQuery

import core.util.IdGenerator
import java.time.Instant

class EmailVerifierService(
    private val repository: EmailVerifierRepository,
    private val sha256Hasher: Sha256Hasher,
    private val emailSender: EmailSender
) {

    suspend fun send(sendEmail: SendEmail): Boolean = dbQuery {

        val code = generateCode()
        val hash = sha256Hasher.hash(code)

        val entity = EmailVerifier(
            id = IdGenerator.generate(),
            email = sendEmail.email,
            codeHash = hash,
            expiresAt = Instant.now().plusSeconds(
                EmailVerifierConst.EXPIRATION_MINUTES.toLong()
            ),
            used = false,
            createdAt = Instant.now()
        )

        repository.create(entity)


        emailSender.send(
            to = sendEmail.email,
            name = sendEmail.email,
            code = code
        )
    }

    suspend fun verify(verifyEmail: VerifyEmail): Boolean = dbQuery {
        val record = repository.findLatestByEmail(verifyEmail.email)
            ?: throw InvalidCredentials()

        if (record.used) return@dbQuery false
        if (record.expiresAt.isBefore(Instant.now())) return@dbQuery false

        val inputHash = sha256Hasher.hash(verifyEmail.code)

        if (inputHash != record.codeHash) return@dbQuery false

        repository.markAsUsed(record.id)
        true
    }

    private fun generateCode(): String =
        (1..EmailVerifierConst.CODE_LENGTH)
            .map { (0..9).random() }
            .joinToString("")
}