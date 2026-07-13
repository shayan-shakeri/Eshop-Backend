package com.shayan.feature.banner.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class BannerResponse(
    val id: String,
    val imageUrl: String,
    val active: Boolean,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)