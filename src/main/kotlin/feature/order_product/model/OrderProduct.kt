package com.shayan.feature.order_product.model

import java.math.BigDecimal

data class OrderProduct(
    val id: String,
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val originalPrice: BigDecimal,
    val finalPrice: BigDecimal
)