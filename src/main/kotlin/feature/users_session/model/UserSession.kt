package com.shayan.feature.users_session.model

import java.time.Instant


data class UserSession(
    val id: String,
    val userId: String,
    val refreshToken: String,
    val deviceId: String,
    val expiresAt: Instant,
    val revoked: Boolean,
    val createdAt: Instant,
    val lastUsedAt: Instant?
)