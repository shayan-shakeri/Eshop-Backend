package com.shayan.feature.category.mapper

import com.shayan.feature.category.dto.CategoryResponse
import com.shayan.feature.category.model.Category

fun Category.toCategoryResponse(url: String): CategoryResponse =
    CategoryResponse(
        id = id,
        name = name,
        url = url
    )