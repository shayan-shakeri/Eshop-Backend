package com.shayan.feature.product_image.mapper

import com.shayan.feature.product_image.model.ProductImage
import com.shayan.feature.product_image.table.ProductImageTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProductImage() = ProductImage(
    id = this[ProductImageTable.id],
    productId = this[ProductImageTable.productId],
    previewImage = this[ProductImageTable.previewImage],
    title = this[ProductImageTable.title]
)