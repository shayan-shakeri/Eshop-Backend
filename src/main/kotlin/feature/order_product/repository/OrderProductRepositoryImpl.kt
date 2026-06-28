package com.shayan.feature.order_product.repository

import com.shayan.feature.order_product.mapper.toOrderProduct
import com.shayan.feature.order_product.model.OrderProduct
import com.shayan.feature.order_product.table.OrderProductTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class OrderProductRepositoryImpl :
    OrderProductRepository {

    override suspend fun add(
        orderProduct: OrderProduct
    ): OrderProduct? {

        OrderProductTable.insert {
            it[id] = orderProduct.id
            it[orderId] = orderProduct.orderId
            it[productId] = orderProduct.productId
            it[quantity] = orderProduct.quantity
            it[originalPrice] = orderProduct.originalPrice
            it[finalPrice] = orderProduct.finalPrice
        }

        return findById(orderProduct.id)
    }

    override suspend fun findById(
        id: String
    ): OrderProduct? =
        OrderProductTable
            .selectAll()
            .where {
                OrderProductTable.id eq id
            }
            .singleOrNull()
            ?.toOrderProduct()

    override suspend fun findByOrderId(
        orderId: String
    ): List<OrderProduct> =
        OrderProductTable
            .selectAll()
            .where {
                OrderProductTable.orderId eq orderId
            }
            .map {
                it.toOrderProduct()
            }

    override suspend fun findByProductId(
        productId: String
    ): List<OrderProduct> =
        OrderProductTable
            .selectAll()
            .where {
                OrderProductTable.productId eq productId
            }
            .map {
                it.toOrderProduct()
            }

    override suspend fun delete(
        id: String
    ) {
        OrderProductTable.deleteWhere {
            OrderProductTable.id eq id
        }
    }

    override suspend fun deleteByOrderId(
        orderId: String
    ) {
        OrderProductTable.deleteWhere {
            OrderProductTable.orderId eq orderId
        }
    }
}