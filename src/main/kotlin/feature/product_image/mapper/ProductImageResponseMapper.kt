package com.shayan.feature.product_image.mapper

import com.shayan.feature.product_image.dto.ProductImageResponse
import com.shayan.feature.product_image.model.ProductImage

fun ProductImage.toProductImageResponse(url: String) = ProductImageResponse(
    id = this.id,
    productId = this.productId,
    previewImage = this.previewImage,
    imageUrl = url,
)