package com.shayan.feature.notification.mapper

import com.shayan.feature.notification.model.Notification
import com.shayan.feature.notification.table.NotificationTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toNotification() =
    Notification(
        id = this[NotificationTable.id],
        userId = this[NotificationTable.userId],
        title = this[NotificationTable.title],
        date = this[NotificationTable.date],
        content = this[NotificationTable.content],
        opened = this[NotificationTable.opened]
    )