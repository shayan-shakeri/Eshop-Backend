package com.shayan.feature.comment.mapper

import com.shayan.feature.comment.model.Comment
import com.shayan.feature.comment.table.CommentTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toComment() =
    Comment(
        id = this[CommentTable.id],
        productId = this[CommentTable.productId],
        userId = this[CommentTable.userId],
        title = this[CommentTable.title],
        content = this[CommentTable.content],
        rating = this[CommentTable.rating],
        purchased = this[CommentTable.purchased],
        createdAt = this[CommentTable.createdAt]
    )