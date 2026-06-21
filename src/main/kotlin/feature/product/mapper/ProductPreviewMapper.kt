package com.shayan.feature.product.mapper

import com.shayan.feature.product.dto.ProductPreviewResponse
import com.shayan.feature.product.model.Product
import java.math.BigDecimal

fun Product.toProductPreviewResponse(
    previewImage: String?,
    discountPrice: BigDecimal?
): ProductPreviewResponse =
    ProductPreviewResponse(
        id = this.id,
        name = this.name,
        price = this.price,
        discountPrice = discountPrice,
        previewImage = previewImage
    )