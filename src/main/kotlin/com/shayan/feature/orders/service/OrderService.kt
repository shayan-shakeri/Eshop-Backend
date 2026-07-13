package com.shayan.feature.order.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.order.constants.OrderConst
import com.shayan.feature.order.dto.AddOrderRequest
import com.shayan.feature.order.model.Order
import com.shayan.feature.order.repository.OrderRepository
import com.shayan.feature.order.util.DeliveryState
import core.database.dbQuery
import core.util.IdGenerator
import feature.orders.dto.OrderResponse
import feature.orders.dto.UpdateOrderRequest
import feature.orders.mapper.toOrderResponse
import io.ktor.server.plugins.NotFoundException
import java.time.Instant

class OrderService(
    private val repository: OrderRepository,
    private val userAuditLogService: AuditLogService
) {

    suspend fun addOrder(
        userId: String,
        request: AddOrderRequest
    ): OrderResponse {
        runCatching {
            userAuditLogService.add(
                userId = userId,
                action = OrderConst.ADD_ACTION,
                ip = request.ip
            )
        }
        return dbQuery {

            val order = Order(
                id = IdGenerator.generate(),
                userId = userId,
                addressId = request.addressId,
                deliveryState = DeliveryState.InTransit,
                deliveryDate = request.deliveryDate,
                originalPrice = request.originalPrice,
                finalPrice = request.finalPrice,
                paymentId = request.paymentId,
                port = request.port,
                createdAt = Instant.now()
            )

            repository.add(order)
                ?.toOrderResponse()
                ?: throw FailedToAdd()
        }
    }
    suspend fun readUserOrders(
        userId: String
    ): List<OrderResponse> =
        dbQuery {

            repository.findByUserId(userId)
                .map {
                    it.toOrderResponse()
                }
        }

    suspend fun readSingle(
        userId: String,
        orderId: String
    ): OrderResponse =
        dbQuery {

            val result =
                repository.findById(orderId)
                    ?: throw NotFoundException()

            if (result.userId != userId) {
                throw NotFoundException()
            }

            result.toOrderResponse()
        }


    suspend fun updateDelivery(
        userId: String,
        request: UpdateOrderRequest
    ): OrderResponse {
        runCatching {
            userAuditLogService.add(
                userId = userId ,
                action = OrderConst.UPDATE_ACTION,
                ip = request.ip
            )
        }
        return dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            val updated = existing.copy(
                deliveryState = request.deliveryState,
                deliveryDate = request.deliveryDate
            )

            repository.update(updated)
                ?.toOrderResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun deleteOrder(
        userId: String,
        request: IdIpDTO
    ) {
        runCatching {
            userAuditLogService.add(
                userId = userId,
                action = OrderConst.DELETE_ACTION,
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