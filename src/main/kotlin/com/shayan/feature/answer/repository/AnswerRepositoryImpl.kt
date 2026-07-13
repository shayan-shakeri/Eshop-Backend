package com.shayan.feature.answer.repository

import com.shayan.feature.answer.mapper.toAnswer
import com.shayan.feature.answer.model.Answer
import com.shayan.feature.answer.table.AnswerTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class AnswerRepositoryImpl : AnswerRepository {

    override suspend fun add(
        answer: Answer
    ): Answer? {

        AnswerTable.insert {
            it[id] = answer.id
            it[userId] = answer.userId
            it[questionCommentId] =
                answer.questionCommentId
            it[type] = answer.type
            it[content] = answer.content
        }

        return findById(answer.id)
    }

    override suspend fun findById(
        id: String
    ): Answer? =
        AnswerTable
            .selectAll()
            .where {
                AnswerTable.id eq id
            }
            .singleOrNull()
            ?.toAnswer()

    override suspend fun findByQuestionCommentId(
        questionCommentId: String
    ): List<Answer> =
        AnswerTable
            .selectAll()
            .where {
                AnswerTable.questionCommentId eq
                        questionCommentId
            }
            .map {
                it.toAnswer()
            }

    override suspend fun update(
        answer: Answer
    ): Answer? {

        AnswerTable.update({
            AnswerTable.id eq answer.id
        }) {
            it[content] = answer.content
        }

        return findById(answer.id)
    }

    override suspend fun delete(
        id: String
    ) {
        AnswerTable.deleteWhere {
            AnswerTable.id eq id
        }
    }
}