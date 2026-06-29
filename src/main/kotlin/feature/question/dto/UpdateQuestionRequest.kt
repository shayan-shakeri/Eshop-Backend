package com.shayan.feature.question.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateQuestionRequest(
    val id: String,
    val content: String
)