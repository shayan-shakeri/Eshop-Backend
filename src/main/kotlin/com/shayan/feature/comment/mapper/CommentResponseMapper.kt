package com.shayan.feature.comment.mapper

import com.shayan.feature.comment.dto.CommentResponse
import com.shayan.feature.comment.model.Comment

fun Comment.toCommentResponse() =
    CommentResponse(
        id = id,
        productId = productId,
        userId = userId,
        title = title,
        content = content,
        rating = rating,
        purchased = purchased,
        createdAt = createdAt
    )