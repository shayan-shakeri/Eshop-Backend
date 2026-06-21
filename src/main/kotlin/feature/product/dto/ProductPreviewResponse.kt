package com.shayan.feature.product.dto

import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import java.math.BigDecimal

@Serializable
data class ProductPreviewResponse(
    val id: String,
    val name: String,

    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,

    @Serializable(with = BigDecimalSerializer::class)
    val discountPrice: BigDecimal?,

    val previewImage: String?
)