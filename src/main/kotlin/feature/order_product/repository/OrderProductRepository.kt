package com.shayan.feature.order_product.repository

import com.shayan.feature.order_product.model.OrderProduct

interface OrderProductRepository {

    suspend fun add(
        orderProduct: OrderProduct
    ): OrderProduct?

    suspend fun findById(
        id: String
    ): OrderProduct?

    suspend fun findByOrderId(
        orderId: String
    ): List<OrderProduct>

    suspend fun findByProductId(
        productId: String
    ): List<OrderProduct>

    suspend fun delete(
        id: String
    )

    suspend fun deleteByOrderId(
        orderId: String
    )
}