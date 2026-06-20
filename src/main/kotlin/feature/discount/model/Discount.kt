package com.shayan.feature.discount.model

import com.shayan.feature.discount.util.DiscountCondition
import java.time.LocalDateTime

data class Discount(
    val id: String,
    val productId: String,
    val userId: String?,
    val value: Int,
    val quantity: Int,
    val conditions: DiscountCondition?,
    val conditionValue: Int?,
    val active: Boolean,
    val endingDate: LocalDateTime?
)