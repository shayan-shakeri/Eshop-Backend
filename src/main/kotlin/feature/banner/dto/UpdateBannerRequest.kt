package com.shayan.feature.banner.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateBannerRequest(
    val id: String,
    val active: Boolean,
    val ip: String
)