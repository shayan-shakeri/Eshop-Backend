package com.shayan.feature.notification.mapper

import com.shayan.feature.notification.dto.NotificationResponse
import com.shayan.feature.notification.model.Notification

fun Notification.toNotificationResponse() =
    NotificationResponse(
        id = id,
        userId = userId,
        title = title,
        date = date,
        content = content,
        opened = opened
    )