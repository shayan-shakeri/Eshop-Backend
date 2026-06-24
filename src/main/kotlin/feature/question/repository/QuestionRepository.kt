package com.shayan.feature.question.repository

import com.shayan.feature.question.model.Question

interface QuestionRepository {

    suspend fun add(
        question: Question
    ): Question?

    suspend fun findById(
        id: String
    ): Question?

    suspend fun findByProductId(
        productId: String
    ): List<Question>

    suspend fun update(
        question: Question
    ): Question?

    suspend fun delete(
        id: String
    )
}