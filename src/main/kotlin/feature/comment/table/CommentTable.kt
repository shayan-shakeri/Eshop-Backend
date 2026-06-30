package com.shayan.feature.comment.table

import com.shayan.feature.comment.constants.CommentConst
import com.shayan.feature.product.table.ProductTable
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object CommentTable : Table(CommentConst.TABLE_NAME) {

    val id =
        varchar(
            CommentConst.ID,
            ANC.ID_LENGTH
        )

    val productId =
        varchar(
            CommentConst.PRODUCT_ID,
            ANC.ID_LENGTH
        ).references(
            ProductTable.id,
            onDelete = ReferenceOption.CASCADE,
            onUpdate = ReferenceOption.RESTRICT
        )

    val userId =
        varchar(
            CommentConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id,
            onDelete = ReferenceOption.CASCADE,
            onUpdate = ReferenceOption.RESTRICT
        )

    val title =
        varchar(
            CommentConst.TITLE,
            CommentConst.TITLE_LENGTH
        )

    val content =
        varchar(
            CommentConst.CONTENT,
            CommentConst.CONTENT_LENGTH
        )

    val rating =
        float(
            CommentConst.RATING
        )

    val purchased =
        bool(
            CommentConst.PURCHASED
        )

    val createdAt =
        datetime(
            CommentConst.CREATED_AT
        )

    override val primaryKey =
        PrimaryKey(id)
}