package com.shayan.feature.error_log.service

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.employee_audit_log.constants.EmployeeAuditLogConst
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.error_log.constants.ErrorLogConst
import com.shayan.feature.error_log.mapper.toErrorLogResponse
import com.shayan.feature.error_log.model.ErrorLog
import com.shayan.feature.error_log.repository.ErrorLogRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.time.Instant

class ErrorLogService(
    private val repository: ErrorLogRepository,
    private val employeeAuditLogService:
    EmployeeAuditLogService
) {

    suspend fun add(
        errorMessage: String
    ) {
        dbQuery {

            repository.add(
                ErrorLog(
                    id = IdGenerator.generate(),
                    errorMessage = errorMessage,
                createdAt = Instant.now()
                )
            )
        }
    }

    suspend fun read() =
        dbQuery {

            repository.readAll()
                .map {
                    it.toErrorLogResponse()
                }
        }

    suspend fun delete(
        employeeId: String,
        roleId: String,
        request: IdIpDTO
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action =
                    ErrorLogConst.DELETE_ACTION,
                ip = request.ip
            )
        }

        dbQuery {

            repository.findById(
                request.id
            ) ?: throw NotFoundException()

            repository.delete(
                request.id
            )
        }
    }

    suspend fun deleteAll(
        employeeId: String,
        roleId: String,
        ip: String
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action =
                    ErrorLogConst.DELETE_ALL_ACTION,
                ip = ip
            )
        }

        dbQuery {
            repository.deleteAll()
        }
    }
}