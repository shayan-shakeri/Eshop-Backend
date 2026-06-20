package com.shayan.feature.product_image.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductImageResponse(
    val id: String,
    val productId: String,
    val previewImage: Boolean,
    val imageUrl: String
)