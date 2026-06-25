package com.shayan.feature.notification.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.notification.dto.AddNotificationRequest
import com.shayan.feature.notification.dto.NotificationResponse
import com.shayan.feature.notification.dto.UpdateNotificationRequest
import com.shayan.feature.notification.mapper.toNotificationResponse
import com.shayan.feature.notification.model.Notification
import com.shayan.feature.notification.repository.NotificationRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import java.time.LocalDate

class NotificationService(
    private val repository: NotificationRepository
) {

    suspend fun addNotification(
        request: AddNotificationRequest
    ): NotificationResponse =
        dbQuery {

            val notification = Notification(
                id = IdGenerator.generate(),
                userId = request.userId,
                title = request.title,
                date = LocalDate.now(),
                content = request.content,
                opened = false
            )

            repository.add(notification)
                ?.toNotificationResponse()
                ?: throw FailedToAdd()
        }

    suspend fun readNotifications(
        userId: String
    ): List<NotificationResponse> =
        dbQuery {

            repository.findByUserId(userId)
                .map {
                    it.toNotificationResponse()
                }
        }

    suspend fun openNotification(
        userId: String,
        request: UpdateNotificationRequest
    ): NotificationResponse =
        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            val updated = existing.copy(
                opened = request.opened
            )

            repository.update(updated)
                ?.toNotificationResponse()
                ?: throw NotFoundException()
        }

    suspend fun deleteNotification(
        userId: String,
        request: IdIpDTO
    ) {

        dbQuery {

            val existing =
                repository.findById(request.id)
                    ?: throw NotFoundException()

            if (existing.userId != userId) {
                throw NotFoundException()
            }

            repository.delete(
                request.id
            )
        }
    }
}