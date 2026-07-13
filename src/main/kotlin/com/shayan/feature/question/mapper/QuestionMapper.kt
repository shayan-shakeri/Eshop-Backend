package com.shayan.feature.question.mapper

import com.shayan.feature.question.model.Question
import com.shayan.feature.question.table.QuestionTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toQuestion(): Question =
    Question(
        id = this[QuestionTable.id],
        userId = this[QuestionTable.userId],
        productId = this[QuestionTable.productId],
        content = this[QuestionTable.content]
    )