package com.shayan.feature.product.model

import com.shayan.util.enums.ProductAge
import com.shayan.util.enums.ProductGender
import java.math.BigDecimal

data class Product(
    val id: String,
    val categoryId: String,
    val filterId: String,
    val name: String,
    val description: String,
    val originalPrice: BigDecimal,
    val price: BigDecimal,
    val stock: Int,
    val size: String,
    val length: String,
    val material: String,
    val gender: ProductGender,
    val age: ProductAge
)