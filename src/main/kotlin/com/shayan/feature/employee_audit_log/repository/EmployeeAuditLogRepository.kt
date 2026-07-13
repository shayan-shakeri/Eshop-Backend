package com.shayan.feature.employee_audit_log.repository

import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog

interface EmployeeAuditLogRepository {
    suspend fun findByEmployeeId(employeeId: String): List<EmployeeAuditLog>

    suspend fun addEmployeeAuditLog(employeeAuditLog: EmployeeAuditLog): EmployeeAuditLog?
}