package com.shayan.feature.notification.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateNotificationRequest(
    val id: String,
    val opened: Boolean
)