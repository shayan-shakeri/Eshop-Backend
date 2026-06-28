package com.shayan.feature.support_chat.repository

import com.shayan.feature.support_chat.model.SupportChat
import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus

interface SupportChatRepository {

    suspend fun add(
        supportChat: SupportChat
    ): SupportChat?

    suspend fun findById(
        id: String
    ): SupportChat?

    suspend fun findByUserId(
        userId: String
    ): List<SupportChat>

    suspend fun findByStatus(
        status: SupportChatStatus
    ): List<SupportChat>

    suspend fun findByPriority(
        priority: SupportChatPriority
    ): List<SupportChat>

    suspend fun readAll(): List<SupportChat>

    suspend fun update(
        supportChat: SupportChat
    ): SupportChat?

    suspend fun delete(
        id: String
    )
}