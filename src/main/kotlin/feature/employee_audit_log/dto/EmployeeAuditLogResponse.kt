package com.shayan.feature.employee_audit_log.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class EmployeeAuditLogResponse (
    val id: String,
    val employeeId: String?,
    val roleId: String,
    val action: String,
    val ip: String,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)