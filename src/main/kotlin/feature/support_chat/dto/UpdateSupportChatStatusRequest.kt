package com.shayan.feature.support_chat.dto

import com.shayan.util.enums.SupportChatStatus
import kotlinx.serialization.Serializable

@Serializable
data class UpdateSupportChatStatusRequest(
    val id: String,
    val status: SupportChatStatus,
    val ip: String
)