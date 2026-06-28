package com.shayan.feature.support_message.model

import com.shayan.util.enums.SupportMessageType

data class SupportMessage(
    val id: String,
    val userId: String?,
    val supportChatId: String,
    val content: String?,
    val title: String?,
    val messageType: SupportMessageType,
    val sequence: Int
)