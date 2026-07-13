package com.shayan.feature.support_chat.model

import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus

data class SupportChat(
    val id: String,
    val userId: String,
    val name: String,
    val priority: SupportChatPriority,
    val status: SupportChatStatus
)