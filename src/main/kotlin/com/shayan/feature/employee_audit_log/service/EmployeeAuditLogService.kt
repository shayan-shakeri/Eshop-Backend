package com.shayan.feature.employee_audit_log.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.employee_audit_log.dto.EmployeeAuditLogResponse
import com.shayan.feature.employee_audit_log.mapper.toEmployeeAuditLogResponse
import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog
import com.shayan.feature.employee_audit_log.repository.EmployeeAuditLogRepository
import core.database.dbQuery
import core.util.IdGenerator
import java.time.Instant

class EmployeeAuditLogService(
    private val repository: EmployeeAuditLogRepository
) {

    suspend fun addAuditLog(employeeId: String, roleId: String, action: String, ip: String): EmployeeAuditLogResponse =
        dbQuery {
            val request = EmployeeAuditLog(
                id = IdGenerator.generate(),
                employeeId = employeeId,
                roleId = roleId,
                action = action,
                ip = ip,
                createdAt = Instant.now()
            )
            repository.addEmployeeAuditLog(request)?.toEmployeeAuditLogResponse() ?: throw FailedToAdd()
        }

    suspend fun readAuditLog(employeeId: String): List<EmployeeAuditLogResponse> = dbQuery {
        repository.findByEmployeeId(employeeId).map { it.toEmployeeAuditLogResponse() }
    }
}