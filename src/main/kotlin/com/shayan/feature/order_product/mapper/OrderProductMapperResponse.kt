package com.shayan.feature.order_product.mapper

import com.shayan.feature.order_product.dto.OrderProductResponse
import com.shayan.feature.order_product.model.OrderProduct

fun OrderProduct.toOrderProductResponse() =
    OrderProductResponse(
        id = id,
        orderId = orderId,
        productId = productId,
        quantity = quantity,
        originalPrice = originalPrice,
        finalPrice = finalPrice
    )