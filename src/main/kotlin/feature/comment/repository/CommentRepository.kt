package com.shayan.feature.comment.repository

import com.shayan.feature.comment.model.Comment

interface CommentRepository {

    suspend fun add(
        comment: Comment
    ): Comment?

    suspend fun findById(
        id: String
    ): Comment?

    suspend fun findByProductId(
        productId: String
    ): List<Comment>

    suspend fun update(
        comment: Comment
    ): Comment?

    suspend fun delete(
        id: String
    )
}