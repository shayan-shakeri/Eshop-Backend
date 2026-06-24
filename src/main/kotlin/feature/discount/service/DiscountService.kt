package com.shayan.feature.discount.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.discount.constants.DiscountConst
import com.shayan.feature.discount.dto.AddDiscountRequest
import com.shayan.feature.discount.dto.DiscountResponse
import com.shayan.feature.discount.dto.UpdateDiscountRequest
import com.shayan.feature.discount.mapper.toDiscountResponse
import com.shayan.feature.discount.model.Discount
import com.shayan.feature.discount.repository.DiscountRepository
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import java.time.LocalDateTime

class DiscountService(
    private val repository: DiscountRepository,
    private val employeeAuditLogService: EmployeeAuditLogService
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        request: AddDiscountRequest
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.ADD_ACTION,
                ip = request.ip
            )
        }

        return dbQuery {

            val discount = Discount(
                id = IdGenerator.generate(),
                productId = request.productId,
                userId = request.userId,
                value = request.value,
                quantity = request.quantity,
                conditions = request.conditions,
                conditionValue = request.conditionValue,
                active = request.active,
                endingDate = request.endingDate
            )

            repository.add(discount)
                ?.toDiscountResponse()
                ?: throw FailedToAdd()
        }
    }

    suspend fun read(
        id: String
    ): DiscountResponse =
        dbQuery {

            repository.findById(id)
                ?.toDiscountResponse()
                ?: throw NotFoundException()
        }

    suspend fun readAll(): List<DiscountResponse> =
        dbQuery {

            repository.findAll()
                .map {
                    it.toDiscountResponse()
                }
        }

    suspend fun readByProduct(
        productId: String
    ): List<DiscountResponse> =
        dbQuery {

            repository.findByProductId(productId)
                .map {
                    it.toDiscountResponse()
                }
        }

    suspend fun readByUser(
        userId: String
    ): List<DiscountResponse> =
        dbQuery {

            repository.findByUserId(userId)
                .map {
                    it.toDiscountResponse()
                }
        }

    suspend fun readActive(): List<DiscountResponse> =
        dbQuery {

            repository.findActive()
                .filter {
                    it.endingDate == null ||
                            it.endingDate.isAfter(LocalDateTime.now())
                }
                .map {
                    it.toDiscountResponse()
                }
        }

    suspend fun update(
        employeeId: String,
        roleId: String,
        request: UpdateDiscountRequest
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.UPDATE_ACTION,
                ip = request.ip
            )
        }

        return dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            val updated = Discount(
                id = existing.id,
                productId = existing.productId,
                userId = request.userId,
                value = request.value,
                quantity = request.quantity,
                conditions = request.conditions,
                conditionValue = request.conditionValue,
                active = request.active,
                endingDate = request.endingDate
            )

            repository.update(updated)
                ?.toDiscountResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun activate(
        employeeId: String,
        roleId: String,
        request: IdIpDTO
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.ACTIVATE_ACTION,
                ip = request.ip
            )
        }

        return dbQuery {

            repository.activate(request.id)
                ?.toDiscountResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun deactivate(
        employeeId: String,
        roleId: String,
        request: IdIpDTO
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.DEACTIVATE_ACTION,
                ip = request.ip
            )
        }

        return dbQuery {

            repository.deactivate(request.id)
                ?.toDiscountResponse()
                ?: throw NotFoundException()
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
                action = DiscountConst.DELETE_ACTION,
                ip = request.ip
            )
        }

        dbQuery {

            repository.findById(request.id)
                ?: throw NotFoundException()

            repository.delete(request.id)
        }
    }

}