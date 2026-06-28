package com.shayan.feature.support_chat.mapper

import com.shayan.feature.support_chat.dto.SupportChatResponse
import com.shayan.feature.support_chat.model.SupportChat

fun SupportChat.toSupportChatResponse() =
    SupportChatResponse(
        id = id,
        userId = userId,
        name = name,
        priority = priority,
        status = status
    )