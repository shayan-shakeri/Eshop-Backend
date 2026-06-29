package com.shayan.feature.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddQuestionRequest(
    val productId: String,
    val content: String
)