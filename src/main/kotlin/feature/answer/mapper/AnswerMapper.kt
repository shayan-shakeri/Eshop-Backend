package com.shayan.feature.answer.mapper

import com.shayan.feature.answer.model.Answer
import com.shayan.feature.answer.table.AnswerTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toAnswer(): Answer =
    Answer(
        id = this[AnswerTable.id],
        userId = this[AnswerTable.userId],
        questionCommentId =
            this[AnswerTable.questionCommentId],
        content =
            this[AnswerTable.content]
    )