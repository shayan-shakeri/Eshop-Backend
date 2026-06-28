package com.shayan.feature.support_message.repository

import com.shayan.feature.support_message.mapper.toSupportMessage
import com.shayan.feature.support_message.model.SupportMessage
import com.shayan.feature.support_message.table.SupportMessageTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class SupportMessageRepositoryImpl :
    SupportMessageRepository {

    override suspend fun add(
        supportMessage: SupportMessage
    ): SupportMessage? {

        SupportMessageTable.insert {
            it[id] = supportMessage.id
            it[userId] = supportMessage.userId
            it[supportChatId] = supportMessage.supportChatId
            it[content] = supportMessage.content
            it[title] = supportMessage.title
            it[messageType] = supportMessage.messageType
            it[sequence] = supportMessage.sequence
        }

        return findById(
            supportMessage.id
        )
    }

    override suspend fun findById(
        id: String
    ): SupportMessage? =
        SupportMessageTable
            .selectAll()
            .where {
                SupportMessageTable.id eq id
            }
            .singleOrNull()
            ?.toSupportMessage()

    override suspend fun findByChatId(
        supportChatId: String
    ): List<SupportMessage> =
        SupportMessageTable
            .selectAll()
            .where {
                SupportMessageTable.supportChatId eq supportChatId
            }
            .orderBy(
                SupportMessageTable.sequence to SortOrder.ASC
            )
            .map {
                it.toSupportMessage()
            }

    override suspend fun findLastSequence(
        supportChatId: String
    ): Int =
        SupportMessageTable
            .select(
                SupportMessageTable.sequence
            )
            .where {
                SupportMessageTable.supportChatId eq supportChatId
            }
            .maxOfOrNull {
                it[SupportMessageTable.sequence]
            }
            ?: 0

    override suspend fun delete(
        id: String
    ) {
        SupportMessageTable.deleteWhere {
            SupportMessageTable.id eq id
        }
    }

    override suspend fun deleteByChatId(
        supportChatId: String
    ) {
        SupportMessageTable.deleteWhere {
            SupportMessageTable.supportChatId eq supportChatId
        }
    }
}