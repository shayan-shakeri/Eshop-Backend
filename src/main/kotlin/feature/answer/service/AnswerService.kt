package com.shayan.feature.answer.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.answer.dto.AddAnswerRequest
import com.shayan.feature.answer.dto.AnswerResponse
import com.shayan.feature.answer.dto.UpdateAnswerRequest
import com.shayan.feature.answer.mapper.toAnswerResponse
import com.shayan.feature.answer.model.Answer
import com.shayan.feature.answer.repository.AnswerRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*

class AnswerService(
    private val repository: AnswerRepository
) {

    suspend fun addAnswer(
        userId: String,
        request: AddAnswerRequest
    ): AnswerResponse =
        dbQuery {

            val answer = Answer(
                id = IdGenerator.generate(),
                userId = userId,
                questionCommentId = request.questionCommentId,
                content = request.content
            )

            repository.add(answer)
                ?.toAnswerResponse()
                ?: throw FailedToAdd()
        }

    suspend fun readAnswers(
        questionCommentId: String
    ): List<AnswerResponse> =
        dbQuery {

            repository.findByQuestionCommentId(
                questionCommentId
            ).map {
                it.toAnswerResponse()
            }
        }

    suspend fun updateAnswer(
        userId: String,
        request: UpdateAnswerRequest
    ): AnswerResponse =
        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            val updated = existing.copy(
                content = request.content
            )

            repository.update(updated)
                ?.toAnswerResponse()
                ?: throw NotFoundException()
        }

    suspend fun deleteAnswer(
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