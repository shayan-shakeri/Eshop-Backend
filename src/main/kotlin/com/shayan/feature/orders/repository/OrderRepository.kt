package com.shayan.feature.order.repository

import com.shayan.feature.order.model.Order

interface OrderRepository {

    suspend fun add(
        order: Order
    ): Order?

    suspend fun findById(
        id: String
    ): Order?

    suspend fun findByUserId(
        userId: String
    ): List<Order>

    suspend fun readAll(): List<Order>

    suspend fun update(
        order: Order
    ): Order?

    suspend fun delete(
        id: String
    )
}