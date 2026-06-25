package com.shayan.feature.answer.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddAnswerRequest(
    val questionCommentId: String,
    val content: String,
    val ip: String
)