package com.shayan.feature.support_chat.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.support_chat.dto.AddSupportChatRequest
import com.shayan.feature.support_chat.dto.SupportChatResponse
import com.shayan.feature.support_chat.dto.UpdateSupportChatPriorityRequest
import com.shayan.feature.support_chat.dto.UpdateSupportChatStatusRequest
import com.shayan.feature.support_chat.mapper.toSupportChatResponse
import com.shayan.feature.support_chat.model.SupportChat
import com.shayan.feature.support_chat.repository.SupportChatRepository
import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*

class SupportChatService(
    private val repository: SupportChatRepository
) {

    suspend fun add(
        userId: String,
        request: AddSupportChatRequest
    ): SupportChatResponse =
        dbQuery {

            val supportChat = SupportChat(
                id = IdGenerator.generate(),
                userId = userId,
                name = request.name,
                priority = request.priority,
                status = SupportChatStatus.AwaitsAnswer
            )

            repository.add(
                supportChat
            )?.toSupportChatResponse()
                ?: throw FailedToAdd()
        }

    suspend fun readById(
        id: String
    ): SupportChatResponse =
        dbQuery {

            repository.findById(id)
                ?.toSupportChatResponse()
                ?: throw NotFoundException()
        }

    suspend fun readByUser(
        userId: String
    ): List<SupportChatResponse> =
        dbQuery {

            repository.findByUserId(userId)
                .map {
                    it.toSupportChatResponse()
                }
        }

    suspend fun readAll(): List<SupportChatResponse> =
        dbQuery {

            repository.readAll()
                .map {
                    it.toSupportChatResponse()
                }
        }

    suspend fun updatePriority(
        request: UpdateSupportChatPriorityRequest
    ): SupportChatResponse =
        dbQuery {

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            repository.update(
                existing.copy(
                    priority = request.priority
                )
            )?.toSupportChatResponse()
                ?: throw NotFoundException()
        }

    suspend fun updateStatus(
        request: UpdateSupportChatStatusRequest
    ): SupportChatResponse =
        dbQuery {

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            repository.update(
                existing.copy(
                    status = request.status
                )
            )?.toSupportChatResponse()
                ?: throw NotFoundException()
        }

    suspend fun delete(
        request: IdIpDTO
    ) {
        dbQuery {

            repository.findById(
                request.id
            ) ?: throw NotFoundException()

            repository.delete(
                request.id
            )
        }
    }
}