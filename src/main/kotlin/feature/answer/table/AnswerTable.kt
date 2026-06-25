package com.shayan.feature.answer.table

import com.shayan.feature.answer.constants.AnswerConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object AnswerTable : Table(AnswerConst.TABLE_NAME) {

    val id =
        varchar(
            AnswerConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            AnswerConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id
        )

    val questionCommentId =
        varchar(
            AnswerConst.QUESTION_COMMENT_ID,
            ANC.ID_LENGTH
        )

    val content =
        varchar(
            AnswerConst.CONTENT,
            AnswerConst.CONTENT_LENGTH
        )

    override val primaryKey =
        PrimaryKey(id)
}