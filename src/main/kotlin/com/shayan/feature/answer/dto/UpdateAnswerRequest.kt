package com.shayan.feature.answer.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAnswerRequest(
    val id: String,
    val content: String
)