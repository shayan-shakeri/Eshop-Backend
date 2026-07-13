package com.shayan.feature.support_chat.repository

import com.shayan.feature.support_chat.mapper.toSupportChat
import com.shayan.feature.support_chat.model.SupportChat
import com.shayan.feature.support_chat.table.SupportChatTable
import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class SupportChatRepositoryImpl : SupportChatRepository {

    override suspend fun add(
        supportChat: SupportChat
    ): SupportChat? {

        SupportChatTable.insert {
            it[id] = supportChat.id
            it[userId] = supportChat.userId
            it[name] = supportChat.name
            it[priority] = supportChat.priority
            it[status] = supportChat.status
        }

        return findById(
            supportChat.id
        )
    }

    override suspend fun findById(
        id: String
    ): SupportChat? =
        SupportChatTable
            .selectAll()
            .where {
                SupportChatTable.id eq id
            }
            .singleOrNull()
            ?.toSupportChat()

    override suspend fun findByUserId(
        userId: String
    ): List<SupportChat> =
        SupportChatTable
            .selectAll()
            .where {
                SupportChatTable.userId eq userId
            }
            .map {
                it.toSupportChat()
            }

    override suspend fun findByStatus(
        status: SupportChatStatus
    ): List<SupportChat> =
        SupportChatTable
            .selectAll()
            .where {
                SupportChatTable.status eq status
            }
            .map {
                it.toSupportChat()
            }

    override suspend fun findByPriority(
        priority: SupportChatPriority
    ): List<SupportChat> =
        SupportChatTable
            .selectAll()
            .where {
                SupportChatTable.priority eq priority
            }
            .map {
                it.toSupportChat()
            }

    override suspend fun readAll():
            List<SupportChat> =
        SupportChatTable
            .selectAll()
            .map {
                it.toSupportChat()
            }

    override suspend fun update(
        supportChat: SupportChat
    ): SupportChat? {

        SupportChatTable.update({
            SupportChatTable.id eq supportChat.id
        }) {
            it[name] = supportChat.name
            it[priority] = supportChat.priority
            it[status] = supportChat.status
        }

        return findById(
            supportChat.id
        )
    }

    override suspend fun delete(
        id: String
    ) {
        SupportChatTable.deleteWhere {
            SupportChatTable.id eq id
        }
    }
}