package com.shayan.feature.comment.dto

import kotlinx.serialization.Serializable
import util.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class CommentResponse(
    val id: String,
    val productId: String,
    val userId: String,
    val title: String,
    val content: String,
    val rating: Float,
    val purchased: Boolean,

    @Serializable(with = LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime
)