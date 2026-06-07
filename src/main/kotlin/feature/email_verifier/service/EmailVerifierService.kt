package com.shayan.feature.email_verifier.service

import com.shayan.core.exception.InvalidCredentials
import com.shayan.core.security.hasher.Sha256Hasher
import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.emailVerifier.model.EmailVerifier
import com.shayan.feature.email_verifier.dto.SendEmail
import com.shayan.feature.email_verifier.dto.VerifyEmail

import com.shayan.feature.email_verifier.repository.EmailVerifierRepository
import com.shayan.feature.sender.constants.EmailVerifierConst

import core.database.dbQuery

import core.util.IdGenerator
import feature.email_verifier.template.VerificationEmailTemplate
import java.time.Instant

class EmailVerifierService(
    private val repository: EmailVerifierRepository,
    private val sha256Hasher: Sha256Hasher,
    private val emailSender: EmailSender,
    private val auditLogService: AuditLogService
) {

    suspend fun send(sendEmail: SendEmail): Boolean = dbQuery {

        val code = generateCode()
        val hash = sha256Hasher.hash(code)

        val entity = EmailVerifier(
            id = IdGenerator.generate(),
            userId = sendEmail.userId,
            email = sendEmail.email,
            codeHash = hash,
            expiresAt = Instant.now().plusSeconds(
                EmailVerifierConst.EXPIRATION_MINUTES.toLong()
            ),
            used = false,
            createdAt = Instant.now()
        )

        repository.create(entity)
        runCatching { auditLogService.add(sendEmail.userId, EmailVerifierConst.SEND_ACTION, sendEmail.ip) }


        emailSender.send(
            to = sendEmail.email,
            name = sendEmail.username,
            code = code
        )
    }

    suspend fun verify(verifyEmail: VerifyEmail): Boolean = dbQuery {
        val record = repository.findLatestByEmail(verifyEmail.email)
            ?: throw InvalidCredentials()

        if (record.used) return@dbQuery false
        if (record.expiresAt.isBefore(Instant.now())) return@dbQuery false
        if (record.userId != verifyEmail.userId) return@dbQuery false

        val inputHash = sha256Hasher.hash(verifyEmail.code)

        if (inputHash != record.codeHash) return@dbQuery false

        runCatching { auditLogService.add(verifyEmail.userId, EmailVerifierConst.SEND_ACTION, verifyEmail.ip) }
        repository.markAsUsed(record.id)
        true
    }

    private fun generateCode(): String =
        (1..EmailVerifierConst.CODE_LENGTH)
            .map { (0..9).random() }
            .joinToString("")
}