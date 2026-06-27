package com.shayan.feature.answer.dto

import com.shayan.util.enums.AnswerType
import kotlinx.serialization.Serializable

@Serializable
data class AnswerResponse(
    val id: String,
    val userId: String,
    val questionCommentId: String,
    val type: AnswerType,
    val content: String
)