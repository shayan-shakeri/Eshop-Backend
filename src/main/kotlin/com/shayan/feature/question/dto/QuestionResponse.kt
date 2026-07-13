package com.shayan.feature.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionResponse(
    val id: String,
    val userId: String,
    val productId: String,
    val content: String
)