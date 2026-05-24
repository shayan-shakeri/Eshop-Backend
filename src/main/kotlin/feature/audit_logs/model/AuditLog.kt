package com.shayan.feature.audit_logs.model

import java.time.Instant


data class AuditLog (
    val id: String,
    val userId: String,
    val action : String,
    val ip: String,
    val createdAt: Instant
)