package com.shayan.feature.support_message.mapper

import com.shayan.feature.support_message.model.SupportMessage
import com.shayan.feature.support_message.table.SupportMessageTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSupportMessage() =
    SupportMessage(
        id = this[SupportMessageTable.id],
        userId = this[SupportMessageTable.userId],
        supportChatId = this[SupportMessageTable.supportChatId],
        content = this[SupportMessageTable.content],
        title = this[SupportMessageTable.title],
        messageType = this[SupportMessageTable.messageType],
        sequence = this[SupportMessageTable.sequence]
    )