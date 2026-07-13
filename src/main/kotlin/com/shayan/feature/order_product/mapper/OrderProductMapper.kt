package com.shayan.feature.order_product.mapper

import com.shayan.feature.order_product.model.OrderProduct
import com.shayan.feature.order_product.table.OrderProductTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toOrderProduct() =
    OrderProduct(
        id = this[OrderProductTable.id],
        orderId = this[OrderProductTable.orderId],
        productId = this[OrderProductTable.productId],
        quantity = this[OrderProductTable.quantity],
        originalPrice = this[OrderProductTable.originalPrice],
        finalPrice = this[OrderProductTable.finalPrice]
    )