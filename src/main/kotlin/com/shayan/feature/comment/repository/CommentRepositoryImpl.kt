package com.shayan.feature.comment.repository

import com.shayan.feature.comment.mapper.toComment
import com.shayan.feature.comment.model.Comment
import com.shayan.feature.comment.table.CommentTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class CommentRepositoryImpl : CommentRepository {

    override suspend fun add(
        comment: Comment
    ): Comment? {

        CommentTable.insert {
            it[id] = comment.id
            it[productId] = comment.productId
            it[userId] = comment.userId
            it[title] = comment.title
            it[content] = comment.content
            it[rating] = comment.rating
            it[purchased] = comment.purchased
            it[createdAt] = comment.createdAt
        }

        return findById(comment.id)
    }

    override suspend fun findById(
        id: String
    ): Comment? =
        CommentTable
            .selectAll()
            .where {
                CommentTable.id eq id
            }
            .singleOrNull()
            ?.toComment()

    override suspend fun findByProductId(
        productId: String
    ): List<Comment> =
        CommentTable
            .selectAll()
            .where {
                CommentTable.productId eq productId
            }
            .map {
                it.toComment()
            }

    override suspend fun update(
        comment: Comment
    ): Comment? {

        CommentTable.update({
            CommentTable.id eq comment.id
        }) {
            it[title] = comment.title
            it[content] = comment.content
            it[rating] = comment.rating
        }

        return findById(comment.id)
    }

    override suspend fun delete(
        id: String
    ) {
        CommentTable.deleteWhere {
            CommentTable.id eq id
        }
    }
}