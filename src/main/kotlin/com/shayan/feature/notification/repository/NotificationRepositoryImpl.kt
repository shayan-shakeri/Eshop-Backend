package com.shayan.feature.notification.repository

import com.shayan.feature.notification.mapper.toNotification
import com.shayan.feature.notification.model.Notification
import com.shayan.feature.notification.table.NotificationTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class NotificationRepositoryImpl : NotificationRepository {

    override suspend fun add(
        notification: Notification
    ): Notification? {

        NotificationTable.insert {
            it[id] = notification.id
            it[userId] = notification.userId
            it[title] = notification.title
            it[date] = notification.date
            it[content] = notification.content
            it[opened] = notification.opened
        }

        return findById(notification.id)
    }

    override suspend fun findById(
        id: String
    ): Notification? =
        NotificationTable
            .selectAll()
            .where {
                NotificationTable.id eq id
            }
            .singleOrNull()
            ?.toNotification()

    override suspend fun findByUserId(
        userId: String
    ): List<Notification> =
        NotificationTable
            .selectAll()
            .where {
                NotificationTable.userId eq userId
            }
            .map {
                it.toNotification()
            }

    override suspend fun update(
        notification: Notification
    ): Notification? {

        NotificationTable.update({
            NotificationTable.id eq notification.id
        }) {
            it[opened] = notification.opened
        }

        return findById(notification.id)
    }

    override suspend fun delete(
        id: String
    ) {
        NotificationTable.deleteWhere {
            NotificationTable.id eq id
        }
    }
}