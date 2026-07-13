package com.shayan.feature.order_product.table

import com.shayan.feature.order.constants.OrderConst
import com.shayan.feature.order.table.OrderTable
import com.shayan.feature.order_product.constants.OrderProductConst
import com.shayan.feature.product.constants.ProductConst
import com.shayan.feature.product.table.ProductTable
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object OrderProductTable : Table(
    OrderProductConst.TABLE_NAME
) {

    val id =
        varchar(
            OrderProductConst.ID,
            ANC.ID_LENGTH
        )

    val orderId =
        varchar(
            OrderProductConst.ORDER_ID,
            ANC.ID_LENGTH
        ).references(
            OrderTable.id
        )

    val productId =
        varchar(
            OrderProductConst.PRODUCT_ID,
            ANC.ID_LENGTH
        ).references(
            ProductTable.id
        )

    val quantity =
        integer(
            OrderProductConst.QUANTITY
        )

    val originalPrice =
        decimal(
            OrderProductConst.ORIGINAL_PRICE,
            10,
            2
        )

    val finalPrice =
        decimal(
            OrderProductConst.FINAL_PRICE,
            10,
            2
        )

    override val primaryKey =
        PrimaryKey(id)
}