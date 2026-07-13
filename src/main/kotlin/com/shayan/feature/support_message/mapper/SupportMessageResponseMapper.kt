package com.shayan.feature.support_message.mapper

import com.shayan.feature.support_message.dto.SupportMessageResponse
import com.shayan.feature.support_message.model.SupportMessage

fun SupportMessage.toSupportMessageResponse() =
    SupportMessageResponse(
        id = id,
        userId = userId,
        supportChatId = supportChatId,
        content = content,
        title = title,
        messageType = messageType,
        sequence = sequence
    )