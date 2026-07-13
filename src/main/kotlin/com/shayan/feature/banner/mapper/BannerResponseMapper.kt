package com.shayan.feature.banner.mapper

import com.shayan.feature.banner.dto.BannerResponse
import com.shayan.feature.banner.model.Banner

fun Banner.toBannerResponse(
    imageUrl: String
) =
    BannerResponse(
        id = id,
        imageUrl = imageUrl,
        active = active,
        createdAt = createdAt
    )