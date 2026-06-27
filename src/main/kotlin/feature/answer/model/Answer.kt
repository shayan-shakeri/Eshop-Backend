package com.shayan.feature.answer.model

import com.shayan.feature.answer.service.AnswerService
import com.shayan.util.enums.AnswerType

data class Answer(
    val id: String,
    val userId: String,
    val questionCommentId: String,
    val type: AnswerType,
    val content: String
)