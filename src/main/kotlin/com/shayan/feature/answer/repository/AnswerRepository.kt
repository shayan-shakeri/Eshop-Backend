package com.shayan.feature.answer.repository

import com.shayan.feature.answer.model.Answer

interface AnswerRepository {

    suspend fun add(
        answer: Answer
    ): Answer?

    suspend fun findById(
        id: String
    ): Answer?

    suspend fun findByQuestionCommentId(
        questionCommentId: String
    ): List<Answer>

    suspend fun update(
        answer: Answer
    ): Answer?

    suspend fun delete(
        id: String
    )
}