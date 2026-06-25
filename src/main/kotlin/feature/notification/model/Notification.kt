package com.shayan.feature.notification.model

import java.time.LocalDate

data class Notification(
    val id: String,
    val userId: String,
    val title: String,
    val date: LocalDate,
    val content: String,
    val opened: Boolean
)