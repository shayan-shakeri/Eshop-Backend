package com.shayan.feature.employee_audit_log.model

import java.time.Instant

data class EmployeeAuditLog (
    val id: String,
    val employeeId: String?,
    val roleId: String,
    val action: String,
    val ip: String,
    val createdAt: Instant
)