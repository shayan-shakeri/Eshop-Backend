package com.shayan.feature.setting.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.setting.constants.SettingConst
import com.shayan.feature.setting.dto.AddSettingRequest
import com.shayan.feature.setting.dto.SettingResponse
import com.shayan.feature.setting.dto.UpdateSettingRequest
import com.shayan.feature.setting.mapper.toSettingResponse
import com.shayan.feature.setting.model.Setting
import com.shayan.feature.setting.repository.SettingRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException

class SettingService(
    private val repository: SettingRepository,
    private val employeeAuditLogService: EmployeeAuditLogService
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        ip: String,
        request: AddSettingRequest
    ): SettingResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = SettingConst.ADD_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val setting = Setting(
                id = IdGenerator.generate(),
                productId = request.productId,
                name = request.name,
                value = request.value
            )

            repository.add(setting)
                ?.toSettingResponse()
                ?: throw FailedToAdd()
        }
    }

    suspend fun read(
        id: String
    ): SettingResponse =
        dbQuery {

            repository.findById(id)
                ?.toSettingResponse()
                ?: throw NotFoundException()
        }

    suspend fun readAll(): List<SettingResponse> =
        dbQuery {

            repository.findAll()
                .map {
                    it.toSettingResponse()
                }
        }

    suspend fun readByProduct(
        productId: String
    ): List<SettingResponse> =
        dbQuery {

            repository.findByProductId(productId)
                .map {
                    it.toSettingResponse()
                }
        }

    suspend fun update(
        employeeId: String,
        roleId: String,
        id: String,
        ip: String,
        request: UpdateSettingRequest
    ): SettingResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = SettingConst.UPDATE_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val existing =
                repository.findById(id)
                    ?: throw NotFoundException()

            val updated = Setting(
                id = existing.id,
                productId = existing.productId,
                name = request.name,
                value = request.value
            )

            repository.update(updated)
                ?.toSettingResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun delete(
        employeeId: String,
        roleId: String,
        id: String,
        ip: String
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = SettingConst.DELETE_ACTION,
                ip = ip
            )
        }

        dbQuery {

            repository.findById(id)
                ?: throw NotFoundException()

            repository.delete(id)
        }
    }
}