package com.shayan.feature.favorites.dto

import kotlinx.serialization.Serializable

@Serializable
data class FavoriteResponse(
    val id: String,
    val userId: String,
    val productId: String
)