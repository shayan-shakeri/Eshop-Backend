package com.shayan.feature.discount.mapper

import com.shayan.feature.discount.dto.DiscountResponse
import com.shayan.feature.discount.model.Discount

fun Discount.toDiscountResponse(): DiscountResponse =
    DiscountResponse(
        id = this.id,
        productId = this.productId,
        userId = this.userId,
        value = this.value,
        quantity = this.quantity,
        conditions = this.conditions,
        conditionValue = this.conditionValue,
        active = this.active,
        endingDate = this.endingDate
    )