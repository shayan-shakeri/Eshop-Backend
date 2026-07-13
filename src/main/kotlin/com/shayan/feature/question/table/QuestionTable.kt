package com.shayan.feature.question.table

import com.shayan.feature.product.table.ProductTable
import com.shayan.feature.question.constants.QuestionConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object QuestionTable : Table(QuestionConst.TABLE_NAME) {

    val id =
        varchar(
            QuestionConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            QuestionConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id
        )

    val productId =
        varchar(
            QuestionConst.PRODUCT_ID,
            ANC.ID_LENGTH
        ).references(
            ProductTable.id
        )

    val content =
        varchar(
            QuestionConst.CONTENT,
            QuestionConst.CONTENT_LENGTH
        )

    override val primaryKey =
        PrimaryKey(id)
}