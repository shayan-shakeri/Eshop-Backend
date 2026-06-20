package com.shayan.feature.discount.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.discount.constants.DiscountConst
import com.shayan.feature.discount.dto.AddDiscountRequest
import com.shayan.feature.discount.dto.DiscountResponse
import com.shayan.feature.discount.dto.UpdateDiscountRequest
import com.shayan.feature.discount.mapper.toDiscountResponse
import com.shayan.feature.discount.model.Discount
import com.shayan.feature.discount.repository.DiscountRepository
import com.shayan.feature.discount.util.DiscountCondition
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
        ip: String,
        request: AddDiscountRequest
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.ADD_ACTION,
                ip = ip
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
        id: String,
        ip: String,
        request: UpdateDiscountRequest
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.UPDATE_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val existing =
                repository.findById(id)
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
        id: String,
        ip: String
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.ACTIVATE_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            repository.activate(id)
                ?.toDiscountResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun deactivate(
        employeeId: String,
        roleId: String,
        id: String,
        ip: String
    ): DiscountResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = DiscountConst.DEACTIVATE_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            repository.deactivate(id)
                ?.toDiscountResponse()
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
                action = DiscountConst.DELETE_ACTION,
                ip = ip
            )
        }

        dbQuery {

            repository.findById(id)
                ?: throw NotFoundException()

            repository.delete(id)
        }
    }

    suspend fun calculateApplicableDiscount(
        productId: String,
        userId: String?,
        quantity: Int
    ): DiscountResponse =
        dbQuery {

            repository.findByProductId(productId)
                .filter {
                    it.active
                }
                .filter {
                    it.endingDate == null ||
                            it.endingDate.isAfter(LocalDateTime.now())
                }
                .filter {
                    it.userId == null ||
                            it.userId == userId
                }
                .filter {

                    when (it.conditions) {

                        null -> true

                        DiscountCondition.HigherThan ->
                            quantity > (it.conditionValue ?: 0)

                        DiscountCondition.LowerThan ->
                            quantity < (
                                    it.conditionValue
                                        ?: Int.MAX_VALUE
                                    )
                    }
                }
                .sortedWith(
                    compareByDescending<Discount> {
                        it.userId == userId &&
                                userId != null
                    }.thenByDescending {
                        it.value
                    }
                )
                .firstOrNull()
                ?.toDiscountResponse()
                ?: throw NotFoundException()
        }
}