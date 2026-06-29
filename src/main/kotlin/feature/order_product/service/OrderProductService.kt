package com.shayan.feature.order_product.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.order.constants.OrderConst
import com.shayan.feature.order_product.dto.AddOrderProductRequest
import com.shayan.feature.order_product.dto.OrderProductResponse
import com.shayan.feature.order_product.mapper.toOrderProductResponse
import com.shayan.feature.order_product.model.OrderProduct
import com.shayan.feature.order_product.repository.OrderProductRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException

class OrderProductService(
    private val repository: OrderProductRepository
) {

    suspend fun add(
        request: AddOrderProductRequest
    ): OrderProductResponse =
        dbQuery {

            val orderProduct = OrderProduct(
                id = IdGenerator.generate(),
                orderId = request.orderId,
                productId = request.productId,
                quantity = request.quantity,
                originalPrice = request.originalPrice,
                finalPrice = request.finalPrice
            )

            repository.add(orderProduct)
                ?.toOrderProductResponse()
                ?: throw FailedToAdd()
        }


    suspend fun readByOrder(
        orderId: String
    ): List<OrderProductResponse> =
        dbQuery {

            repository.findByOrderId(orderId)
                .map {
                    it.toOrderProductResponse()
                }
        }

    suspend fun readByProduct(
        productId: String
    ): List<OrderProductResponse> =
        dbQuery {

            repository.findByProductId(productId)
                .map {
                    it.toOrderProductResponse()
                }
        }

    suspend fun delete(
        request: IdIpDTO
    ) {
        dbQuery {

            repository.findById(
                request.id
            ) ?: throw NotFoundException()

            repository.delete(
                request.id
            )
        }
    }
}