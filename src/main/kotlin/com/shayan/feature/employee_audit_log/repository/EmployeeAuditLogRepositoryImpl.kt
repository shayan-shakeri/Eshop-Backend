package com.shayan.feature.employee_audit_log.repository

import com.shayan.feature.employee_audit_log.mapper.toEmployeeAuditLog
import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog
import com.shayan.feature.employee_audit_log.table.EmployeeAuditLogTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class EmployeeAuditLogRepositoryImpl: EmployeeAuditLogRepository {
    override suspend fun findByEmployeeId(employeeId: String): List<EmployeeAuditLog> =
        EmployeeAuditLogTable
            .selectAll()
            .where { EmployeeAuditLogTable.employee_id eq employeeId }
            .map { it.toEmployeeAuditLog() }


    override suspend fun addEmployeeAuditLog(employeeAuditLog: EmployeeAuditLog): EmployeeAuditLog? {
        EmployeeAuditLogTable.insert {
            it[EmployeeAuditLogTable.id] = employeeAuditLog.id
            it[EmployeeAuditLogTable.employee_id] = employeeAuditLog.employeeId
            it[EmployeeAuditLogTable.role_id] = employeeAuditLog.roleId
            it[EmployeeAuditLogTable.action] = employeeAuditLog.action
            it[EmployeeAuditLogTable.ip] = employeeAuditLog.ip
            it[EmployeeAuditLogTable.created_at] = employeeAuditLog.createdAt
        }

        return EmployeeAuditLogTable
            .selectAll()
            .where { EmployeeAuditLogTable.id eq employeeAuditLog.id }
            .singleOrNull()
            ?.toEmployeeAuditLog()
    }
}