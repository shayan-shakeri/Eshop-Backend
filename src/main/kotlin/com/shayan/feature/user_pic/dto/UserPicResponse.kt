package com.shayan.feature.user_pic.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserPicResponse (
    val id: String,
    val userId: String,
    val title: String,
    val imageUrl: String
) {
}