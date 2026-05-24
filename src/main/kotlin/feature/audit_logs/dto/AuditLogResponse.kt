package com.shayan.feature.audit_logs.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class AuditLogResponse(
    val id: String,
    val userId: String,
    val action: String,
    val ip: String,
    @Contextual val createdAt: Instant
)