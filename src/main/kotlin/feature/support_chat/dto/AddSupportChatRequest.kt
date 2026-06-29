package com.shayan.feature.support_chat.dto

import com.shayan.util.enums.SupportChatPriority
import kotlinx.serialization.Serializable

@Serializable
data class AddSupportChatRequest(
    val name: String,
    val priority: SupportChatPriority
)