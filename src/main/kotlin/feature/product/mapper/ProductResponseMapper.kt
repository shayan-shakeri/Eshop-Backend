package com.shayan.feature.product.mapper

import com.shayan.feature.product.dto.ProductResponse
import com.shayan.feature.product.model.Product

fun Product.toProductResponse(): ProductResponse =
    ProductResponse(
        id = this.id,
        categoryId = this.categoryId,
        filterId = this.filterId,
        name = this.name,
        description = this.description,
        originalPrice = this.originalPrice,
        price = this.price,
        stock = this.stock,
        size = this.size,
        length = this.length,
        material = this.material,
        gender = this.gender,
        age = this.age
    )