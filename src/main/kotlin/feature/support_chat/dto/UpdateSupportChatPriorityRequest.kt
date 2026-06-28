package com.shayan.feature.support_chat.dto

import com.shayan.util.enums.SupportChatPriority
import kotlinx.serialization.Serializable

@Serializable
data class UpdateSupportChatPriorityRequest(
    val id: String,
    val priority: SupportChatPriority,
    val ip: String
)