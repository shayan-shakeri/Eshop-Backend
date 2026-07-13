package com.shayan.feature.support_message.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddSupportTextMessageRequest(
    val supportChatId: String,
    val content: String
)