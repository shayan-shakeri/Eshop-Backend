package com.shayan.feature.answer.mapper

import com.shayan.feature.answer.model.Answer
import com.shayan.feature.answer.table.AnswerTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toAnswer() =
    Answer(
        id = this[AnswerTable.id],
        userId = this[AnswerTable.userId],
        questionCommentId = this[AnswerTable.questionCommentId],
        type = this[AnswerTable.type],
        content = this[AnswerTable.content]
    )