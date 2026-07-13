package com.shayan.feature.order_product.dto

import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import java.math.BigDecimal

@Serializable
data class AddOrderProductRequest(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    @Serializable(with = BigDecimalSerializer::class)
    val originalPrice: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)
    val finalPrice: BigDecimal
)