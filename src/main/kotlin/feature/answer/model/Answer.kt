package com.shayan.feature.answer.model

data class Answer(
    val id: String,
    val userId: String,
    val questionCommentId: String,
    val content: String
)