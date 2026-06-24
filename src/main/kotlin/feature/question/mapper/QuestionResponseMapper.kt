package com.shayan.feature.question.mapper

import com.shayan.feature.question.dto.QuestionResponse
import com.shayan.feature.question.model.Question

fun Question.toQuestionResponse(): QuestionResponse =
    QuestionResponse(
        id = id,
        userId = userId,
        productId = productId,
        content = content
    )