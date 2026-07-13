package com.shayan.feature.support_chat.mapper

import com.shayan.feature.support_chat.model.SupportChat
import com.shayan.feature.support_chat.table.SupportChatTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSupportChat() =
    SupportChat(
        id = this[SupportChatTable.id],
        userId = this[SupportChatTable.userId],
        name = this[SupportChatTable.name],
        priority = this[SupportChatTable.priority],
        status = this[SupportChatTable.status]
    )