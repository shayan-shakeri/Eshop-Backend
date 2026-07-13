package com.shayan.feature.product.dto

import com.shayan.util.enums.ProductAge
import com.shayan.util.enums.ProductGender
import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import java.math.BigDecimal

@Serializable
data class ProductResponse(
    val id: String,
    val categoryId: String,
    val filterId: String,
    val name: String,
    val description: String,

    @Serializable(with = BigDecimalSerializer::class)
    val originalPrice: BigDecimal,

    @Serializable(with = BigDecimalSerializer::class)
    val price: BigDecimal,

    val stock: Int,
    val size: String,
    val length: String,
    val material: String,
    val gender: ProductGender,
    val age: ProductAge
)