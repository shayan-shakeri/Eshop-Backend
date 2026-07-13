package com.shayan.feature.support_chat.dto

import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus
import kotlinx.serialization.Serializable

@Serializable
data class SupportChatResponse(
    val id: String,
    val userId: String,
    val name: String,
    val priority: SupportChatPriority,
    val status: SupportChatStatus
)