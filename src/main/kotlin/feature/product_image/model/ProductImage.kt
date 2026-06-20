package com.shayan.feature.product_image.model

data class ProductImage(
    val id: String,
    val productId: String,
    val previewImage: Boolean,
    val title: String
)