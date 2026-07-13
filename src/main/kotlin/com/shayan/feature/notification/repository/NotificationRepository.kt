package com.shayan.feature.notification.repository

import com.shayan.feature.notification.model.Notification

interface NotificationRepository {

    suspend fun add(
        notification: Notification
    ): Notification?

    suspend fun findById(
        id: String
    ): Notification?

    suspend fun findByUserId(
        userId: String
    ): List<Notification>

    suspend fun update(
        notification: Notification
    ): Notification?

    suspend fun delete(
        id: String
    )
}