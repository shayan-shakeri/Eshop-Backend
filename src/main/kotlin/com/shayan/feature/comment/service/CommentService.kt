package com.shayan.feature.comment.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.comment.dto.AddCommentRequest
import com.shayan.feature.comment.dto.CommentResponse
import com.shayan.feature.comment.dto.UpdateCommentRequest
import com.shayan.feature.comment.mapper.toCommentResponse
import com.shayan.feature.comment.model.Comment
import com.shayan.feature.comment.repository.CommentRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.time.LocalDateTime

class CommentService(
    private val repository: CommentRepository
) {

    suspend fun addComment(
        userId: String,
        request: AddCommentRequest
    ): CommentResponse =
        dbQuery {

            val comment = Comment(
                id = IdGenerator.generate(),
                productId = request.productId,
                userId = userId,
                title = request.title,
                content = request.content,
                rating = request.rating,
                purchased = request.purchased,
                createdAt = LocalDateTime.now()
            )

            repository.add(comment)
                ?.toCommentResponse()
                ?: throw FailedToAdd()
        }

    suspend fun readComments(
        productId: String
    ): List<CommentResponse> =
        dbQuery {

            repository.findByProductId(productId)
                .map {
                    it.toCommentResponse()
                }
        }

    suspend fun updateComment(
        userId: String,
        request: UpdateCommentRequest
    ): CommentResponse =
        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            val updated = Comment(
                id = existing.id,
                productId = existing.productId,
                userId = existing.userId,
                title = request.title,
                content = request.content,
                rating = request.rating,
                purchased = existing.purchased,
                createdAt = existing.createdAt
            )

            repository.update(updated)
                ?.toCommentResponse()
                ?: throw NotFoundException()
        }

    suspend fun deleteComment(
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