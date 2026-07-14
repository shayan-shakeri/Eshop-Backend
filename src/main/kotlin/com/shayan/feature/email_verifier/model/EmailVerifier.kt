package com.shayan.feature.email_verifier.model

import java.time.Instant

data class EmailVerifier(
    val id: String,
    val email: String,
    val codeHash: String,
    val expiresAt: Instant,
    val used: Boolean,
    val createdAt: Instant
)