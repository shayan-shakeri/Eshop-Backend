package com.shayan.feature.support_message.repository

import com.shayan.feature.support_message.model.SupportMessage

interface SupportMessageRepository {

    suspend fun add(
        supportMessage: SupportMessage
    ): SupportMessage?

    suspend fun findById(
        id: String
    ): SupportMessage?

    suspend fun findByChatId(
        supportChatId: String
    ): List<SupportMessage>

    suspend fun findLastSequence(
        supportChatId: String
    ): Int

    suspend fun delete(
        id: String
    )

    suspend fun deleteByChatId(
        supportChatId: String
    )
}