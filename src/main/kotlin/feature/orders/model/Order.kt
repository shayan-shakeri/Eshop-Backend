package com.shayan.feature.order.model

import com.shayan.feature.order.util.DeliveryState
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.Instant

data class Order(
    val id: String,
    val userId: String,
    val addressId: String,
    val deliveryState: DeliveryState,
    val deliveryDate: LocalDateTime,
    val originalPrice: BigDecimal,
    val finalPrice: BigDecimal,
    val paymentId: String,
    val port: String,
    val createdAt: Instant
)