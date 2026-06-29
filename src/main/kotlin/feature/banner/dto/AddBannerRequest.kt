package com.shayan.feature.banner.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddBannerRequest(
    val active: Boolean,
    val ip: String
)