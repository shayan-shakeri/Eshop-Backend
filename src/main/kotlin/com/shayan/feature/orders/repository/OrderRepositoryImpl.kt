package com.shayan.feature.order.repository

import com.shayan.feature.order.model.Order
import com.shayan.feature.order.table.OrderTable
import feature.orders.mapper.toOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class OrderRepositoryImpl : OrderRepository {

    override suspend fun add(
        order: Order
    ): Order? {

        OrderTable.insert {
            it[id] = order.id
            it[userId] = order.userId
            it[addressId] = order.addressId
            it[deliveryState] = order.deliveryState
            it[deliveryDate] = order.deliveryDate
            it[originalPrice] = order.originalPrice
            it[finalPrice] = order.finalPrice
            it[paymentId] = order.paymentId
            it[port] = order.port
            it[createdAt] = order.createdAt
        }

        return findById(order.id)
    }

    override suspend fun findById(
        id: String
    ): Order? =
        OrderTable
            .selectAll()
            .where {
                OrderTable.id eq id
            }
            .singleOrNull()
            ?.toOrder()

    override suspend fun findByUserId(
        userId: String
    ): List<Order> =
        OrderTable
            .selectAll()
            .where {
                OrderTable.userId eq userId
            }
            .map {
                it.toOrder()
            }

    override suspend fun readAll(): List<Order> =
        OrderTable
            .selectAll()
            .map {
                it.toOrder()
            }

    override suspend fun update(
        order: Order
    ): Order? {

        OrderTable.update({
            OrderTable.id eq order.id
        }) {
            it[deliveryState] = order.deliveryState
            it[deliveryDate] = order.deliveryDate
        }

        return findById(order.id)
    }

    override suspend fun delete(
        id: String
    ) {
        OrderTable.deleteWhere {
            OrderTable.id eq id
        }
    }
}