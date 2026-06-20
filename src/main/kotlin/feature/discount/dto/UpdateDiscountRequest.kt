package com.shayan.feature.discount.dto

import com.shayan.feature.discount.util.DiscountCondition
import kotlinx.serialization.Serializable
import util.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class UpdateDiscountRequest(
    val userId: String?,
    val value: Int,
    val quantity: Int,
    val conditions: DiscountCondition?,
    val conditionValue: Int?,
    val active: Boolean,

    @Serializable(with = LocalDateTimeSerializer::class)
    val endingDate: LocalDateTime?
)