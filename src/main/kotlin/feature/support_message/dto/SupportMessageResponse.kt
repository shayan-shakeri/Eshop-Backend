package com.shayan.feature.support_message.dto

import com.shayan.util.enums.SupportMessageType
import kotlinx.serialization.Serializable

@Serializable
data class SupportMessageResponse(
    val id: String,
    val userId: String?,
    val supportChatId: String,
    val content: String?,
    val title: String?,
    val messageType: SupportMessageType,
    val sequence: Int
)