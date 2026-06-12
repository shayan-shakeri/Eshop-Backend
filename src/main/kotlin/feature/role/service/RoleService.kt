package com.shayan.feature.role.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.role.constants.RoleConst
import com.shayan.feature.role.dto.AddRole
import com.shayan.feature.role.dto.RoleResponse
import com.shayan.feature.role.mapper.toRoleResponse
import com.shayan.feature.role.model.Role
import com.shayan.feature.role.repository.RoleRepository
import core.database.dbQuery
import core.util.IdGenerator

class RoleService(
    private val repository: RoleRepository,
    private val employeeAuditLogService: EmployeeAuditLogService
) {
    suspend fun add(addRole: AddRole, employeeId: String, roleId: String): RoleResponse  {
        runCatching { employeeAuditLogService.addAuditLog(employeeId, roleId, RoleConst.ADD_ACTION, addRole.ip) }
        return dbQuery {
            val request = Role(
                id = IdGenerator.generate(),
                title = addRole.title,
                description = addRole.description,
                code = addRole.code
            )
            repository.add(request)?.toRoleResponse() ?: throw FailedToAdd()
        }
    }
    suspend fun read(id: String): RoleResponse = dbQuery{
        repository.finRoleById(id)?.toRoleResponse() ?: throw FailedToAdd()
    }
}