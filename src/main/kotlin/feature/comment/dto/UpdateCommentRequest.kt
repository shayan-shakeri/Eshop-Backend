package com.shayan.feature.comment.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateCommentRequest(
    val id: String,
    val title: String,
    val content: String,
    val rating: Float
)