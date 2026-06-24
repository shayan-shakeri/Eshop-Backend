package com.shayan.feature.comment.model

import java.time.LocalDateTime

data class Comment(
    val id: String,
    val productId: String,
    val userId: String,
    val title: String,
    val content: String,
    val rating: Float,
    val purchased: Boolean,
    val createdAt: LocalDateTime
)