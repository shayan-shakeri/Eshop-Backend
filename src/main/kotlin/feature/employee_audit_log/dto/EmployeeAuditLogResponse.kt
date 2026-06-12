package com.shayan.feature.employee_audit_log.dto

import java.time.Instant

data class EmployeeAuditLogResponse (
    val id: String,
    val employeeId: String,
    val roleId: String,
    val action: String,
    val ip: String,
    val createdAt: Instant
)