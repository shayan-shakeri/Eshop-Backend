package com.shayan.feature.emailVerifier.model

import java.time.Instant
import java.time.LocalDateTime

data class EmailVerifier(
    val id: String,
    val userId: String?,
    val email: String,
    val codeHash: String,
    val expiresAt: Instant,
    val used: Boolean,
    val createdAt: Instant
)