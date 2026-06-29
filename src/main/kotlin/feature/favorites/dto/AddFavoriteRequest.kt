package com.shayan.feature.favorites.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddFavoriteRequest(
    val productId: String,
    val ip: String
)