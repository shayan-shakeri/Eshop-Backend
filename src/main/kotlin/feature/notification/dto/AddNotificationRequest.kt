package com.shayan.feature.notification.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddNotificationRequest(
    val userId: String,
    val title: String,
    val content: String,
    val ip: String
)