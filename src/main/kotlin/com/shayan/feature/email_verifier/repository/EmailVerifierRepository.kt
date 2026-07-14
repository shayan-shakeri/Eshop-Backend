package com.shayan.feature.email_verifier.repository

import com.shayan.feature.email_verifier.model.EmailVerifier


interface EmailVerifierRepository {

    suspend fun create(entity: EmailVerifier): EmailVerifier

    suspend fun findLatestByEmail(email: String): EmailVerifier?

    suspend fun markAsUsed(id: String): Boolean

    suspend fun deleteExpired()
}