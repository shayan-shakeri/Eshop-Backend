package com.shayan.feature.employee_audit_log.mapper

import com.shayan.feature.employee_audit_log.dto.EmployeeAuditLogResponse
import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog

fun EmployeeAuditLog.toEmployeeAuditLogResponse(): EmployeeAuditLogResponse =
    EmployeeAuditLogResponse(
        id = this.id,
        employeeId = this.employeeId,
        roleId = this.roleId,
        action = this.action,
        ip = this.ip,
        createdAt = this.createdAt
    )