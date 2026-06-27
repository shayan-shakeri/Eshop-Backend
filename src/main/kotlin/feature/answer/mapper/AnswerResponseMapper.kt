package com.shayan.feature.answer.mapper

import com.shayan.feature.answer.dto.AnswerResponse
import com.shayan.feature.answer.model.Answer

fun Answer.toAnswerResponse() =
    AnswerResponse(
        id = id,
        userId = userId,
        questionCommentId = questionCommentId,
        type = type,
        content = content
    )