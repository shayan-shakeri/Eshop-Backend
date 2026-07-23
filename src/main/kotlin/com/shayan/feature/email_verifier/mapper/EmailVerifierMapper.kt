package com.shayan.feature.email_verifier.mapper


import com.shayan.feature.email_verifier.model.EmailVerifier
import com.shayan.feature.email_verifier.table.EmailVerifierTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toEmailVerifier(): EmailVerifier = EmailVerifier(
    id = this[EmailVerifierTable.id],
    email = this[EmailVerifierTable.email],
    codeHash = this[EmailVerifierTable.codeHash],
    expiresAt = this[EmailVerifierTable.expiresAt],
    used = this[EmailVerifierTable.used],
    createdAt = this[EmailVerifierTable.createdAt]
)