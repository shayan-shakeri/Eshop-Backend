package com.shayan.feature.product_image.repository

import com.shayan.feature.product_image.model.ProductImage

interface ProductImageRepository {

    suspend fun add(
        productImage: ProductImage
    ): ProductImage?

    suspend fun findById(
        id: String
    ): ProductImage?

    suspend fun findPreview(
        productId: String
    ): ProductImage?

    suspend fun findAll(
        productId: String
    ): List<ProductImage>

    suspend fun clearPreview(
        productId: String
    )

    suspend fun setPreview(
        imageId: String
    ): ProductImage?

    suspend fun update(
        productImage: ProductImage
    ): ProductImage?

    suspend fun delete(
        id: String
    )

    suspend fun deleteAll(
        productId: String
    )
}