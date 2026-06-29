package com.shayan.feature.question.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.question.dto.AddQuestionRequest
import com.shayan.feature.question.dto.QuestionResponse
import com.shayan.feature.question.dto.UpdateQuestionRequest
import com.shayan.feature.question.mapper.toQuestionResponse
import com.shayan.feature.question.model.Question
import com.shayan.feature.question.repository.QuestionRepository
import com.shayan.feature.users.service.UsersService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException

class QuestionService(
    private val repository: QuestionRepository
) {

    suspend fun addQuestion(
        userId: String,
        request: AddQuestionRequest
    ): QuestionResponse =
        dbQuery {

            val question = Question(
                id = IdGenerator.generate(),
                userId = userId,
                productId = request.productId,
                content = request.content
            )

            repository.add(question)
                ?.toQuestionResponse()
                ?: throw FailedToAdd()
        }

    suspend fun readQuestions(
        productId: String
    ): List<QuestionResponse> =
        dbQuery {

            repository.findByProductId(productId)
                .map {
                    it.toQuestionResponse()
                }
        }

    suspend fun updateQuestion(
        userId: String,
        request: UpdateQuestionRequest
    ): QuestionResponse =
        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            val updated = Question(
                id = existing.id,
                userId = existing.userId,
                productId = existing.productId,
                content = request.content
            )

            repository.update(updated)
                ?.toQuestionResponse()
                ?: throw NotFoundException()
        }

    suspend fun deleteQuestion(
        userId: String,
        request: IdIpDTO
    ) {

        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            repository.delete(request.id)
        }
    }
}