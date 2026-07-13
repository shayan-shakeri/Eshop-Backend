package com.shayan.feature.discount.mapper


import com.shayan.feature.discount.model.Discount
import com.shayan.feature.discount.table.DiscountTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toDiscount(): Discount =
    Discount(
        id = this[DiscountTable.id],
        productId = this[DiscountTable.productId],
        userId = this[DiscountTable.userId],
        value = this[DiscountTable.value],
        quantity = this[DiscountTable.quantity],
        conditions = this[DiscountTable.conditions],
        conditionValue = this[DiscountTable.conditionValue],
        active = this[DiscountTable.active],
        endingDate = this[DiscountTable.endingDate]
    )