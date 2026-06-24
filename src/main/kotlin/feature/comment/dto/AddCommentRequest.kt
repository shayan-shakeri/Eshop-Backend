package com.shayan.feature.comment.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddCommentRequest(
    val productId: String,
    val title: String,
    val content: String,
    val rating: Float,
    val purchased: Boolean,
    val ip: String
)