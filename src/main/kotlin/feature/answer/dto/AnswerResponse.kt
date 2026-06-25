package com.shayan.feature.answer.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnswerResponse(
    val id: String,
    val userId: String,
    val questionCommentId: String,
    val content: String
)