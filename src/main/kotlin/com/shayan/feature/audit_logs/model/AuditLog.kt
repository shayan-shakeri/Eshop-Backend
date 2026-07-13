package com.shayan.feature.audit_logs.model

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class AuditLog(
    val id: String,
    val userId: String?,
    val action: String,
    val ip: String,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)