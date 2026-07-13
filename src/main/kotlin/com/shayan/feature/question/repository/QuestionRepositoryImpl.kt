package com.shayan.feature.question.repository

import com.shayan.feature.question.mapper.toQuestion
import com.shayan.feature.question.model.Question
import com.shayan.feature.question.table.QuestionTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class QuestionRepositoryImpl : QuestionRepository {

    override suspend fun add(
        question: Question
    ): Question? {

        QuestionTable.insert {
            it[id] = question.id
            it[userId] = question.userId
            it[productId] = question.productId
            it[content] = question.content
        }

        return findById(question.id)
    }

    override suspend fun findById(
        id: String
    ): Question? =
        QuestionTable
            .selectAll()
            .where {
                QuestionTable.id eq id
            }
            .singleOrNull()
            ?.toQuestion()

    override suspend fun findByProductId(
        productId: String
    ): List<Question> =
        QuestionTable
            .selectAll()
            .where {
                QuestionTable.productId eq productId
            }
            .map {
                it.toQuestion()
            }

    override suspend fun update(
        question: Question
    ): Question? {

        QuestionTable.update({
            QuestionTable.id eq question.id
        }) {
            it[content] = question.content
        }

        return findById(question.id)
    }

    override suspend fun delete(
        id: String
    ) {
        QuestionTable.deleteWhere {
            QuestionTable.id eq id
        }
    }
}