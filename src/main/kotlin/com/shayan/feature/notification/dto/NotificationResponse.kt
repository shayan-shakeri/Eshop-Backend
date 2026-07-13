package com.shayan.feature.notification.dto

import com.shayan.util.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class NotificationResponse(
    val id: String,
    val userId: String,
    val title: String,

    @Serializable(
        with = LocalDateSerializer::class
    )
    val date: LocalDate,

    val content: String,
    val opened: Boolean
)