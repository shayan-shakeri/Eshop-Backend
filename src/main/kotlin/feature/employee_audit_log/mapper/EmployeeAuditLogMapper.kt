package com.shayan.feature.employee_audit_log.mapper

import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog
import com.shayan.feature.employee_audit_log.table.EmployeeAuditLogTable
import org.jetbrains.exposed.sql.ResultRow


fun ResultRow.toEmployeeAuditLog(): EmployeeAuditLog =
    EmployeeAuditLog(
        id = this[EmployeeAuditLogTable.id],
        employeeId = this[EmployeeAuditLogTable.employee_id],
        roleId = this[EmployeeAuditLogTable.role_id],
        action = this[EmployeeAuditLogTable.action],
        ip = this[EmployeeAuditLogTable.ip],
        createdAt = this[EmployeeAuditLogTable.created_at]
    )