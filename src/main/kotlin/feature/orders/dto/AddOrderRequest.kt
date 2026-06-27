package com.shayan.feature.order.dto

import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import util.serializer.LocalDateTimeSerializer
import java.math.BigDecimal
import java.time.LocalDateTime

@Serializable
data class AddOrderRequest(
    val addressId: String,

    @Serializable(with = BigDecimalSerializer::class)
    val originalPrice: BigDecimal,

    @Serializable(with = BigDecimalSerializer::class)
    val finalPrice: BigDecimal,

    @Serializable(
        with = LocalDateTimeSerializer::class
    )
    val deliveryDate: LocalDateTime,

    val paymentId: String,

    val port: String,

    val ip: String
)