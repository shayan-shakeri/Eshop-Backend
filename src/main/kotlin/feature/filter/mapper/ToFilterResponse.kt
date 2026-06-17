package com.shayan.feature.filter.mapper

import com.shayan.feature.filter.dto.FilterResponse
import com.shayan.feature.filter.model.Filter

fun Filter.toFilterResponse(url: String) : FilterResponse =
    FilterResponse(
        id = this.id,
        categoryId = this.categoryId,
        name = this.name,
        url = url
    )