package com.shayan.feature.support_message.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.notification.dto.AddNotificationRequest
import com.shayan.feature.notification.service.NotificationService
import com.shayan.feature.support_chat.service.SupportChatService
import com.shayan.feature.support_message.constants.SupportMessageConst
import com.shayan.feature.support_message.dto.AddSupportTextMessageRequest
import com.shayan.feature.support_message.dto.SupportMessageResponse
import com.shayan.feature.support_message.mapper.toSupportMessageResponse
import com.shayan.feature.support_message.model.SupportMessage
import com.shayan.feature.support_message.repository.SupportMessageRepository
import com.shayan.feature.support_message.websocket.SupportSessionManager
import com.shayan.util.enums.SupportMessageType
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import io.ktor.websocket.*
import kotlinx.serialization.json.Json

class SupportMessageService(
    private val repository: SupportMessageRepository,
    private val chatService: SupportChatService,
    private val imageController: ImageController,
    private val notificationService: NotificationService
) {

    suspend fun addText(
        userId: String,
        roleCode: String?,
        request: AddSupportTextMessageRequest
    ): SupportMessageResponse {
        if (roleCode != "null") {
            val result = chatService.readById(request.supportChatId)
            val notifRequest = AddNotificationRequest(
                userId = result.userId,
                content = request.content,
                title = "New message in the chat: ${result.name}",
            )
            notificationService.addNotification(notifRequest)
        }
        return dbQuery {

            val sequence =
                repository.findLastSequence(
                    request.supportChatId
                ) + 1

            val message = SupportMessage(
                id = IdGenerator.generate(),
                userId = userId,
                supportChatId = request.supportChatId,
                content = request.content,
                title = null,
                messageType = SupportMessageType.Text,
                sequence = sequence
            )

            val result =
                repository.add(message)
                    ?.toSupportMessageResponse()
                    ?: throw FailedToAdd()

            broadcast(result)

            result
        }
    }

    suspend fun addImage(
        userId: String?,
        supportChatId: String,
        fileBytes: ByteArray,
        originalFileName: String?,
        baseUrl: String
    ): SupportMessageResponse =
        dbQuery {

            val fileName =
                imageController.addImage(
                    fileBytes = fileBytes,
                    title = originalFileName,
                    imageType = ImageType.Support
                )


            val sequence =
                repository.findLastSequence(
                    supportChatId
                ) + 1

            val message = SupportMessage(
                id = IdGenerator.generate(),
                userId = userId,
                supportChatId = supportChatId,
                content = null,
                title = fileName,
                messageType = SupportMessageType.Image,
                sequence = sequence
            )


            val result =
                repository.add(message)
                    ?.toSupportMessageResponse()
                    ?: throw FailedToAdd()


            broadcast(
                result.copy(
                    title =
                        "$baseUrl${SupportMessageConst.IMAGE_ROUTE}/$fileName"
                )
            )

            result
        }

    suspend fun read(
        supportChatId: String,
        baseUrl: String
    ): List<SupportMessageResponse> =
        dbQuery {

            repository.findByChatId(
                supportChatId
            ).map {

                if (
                    it.messageType ==
                    SupportMessageType.Image
                ) {
                    it.toSupportMessageResponse().copy(
                        title =
                            "$baseUrl${SupportMessageConst.IMAGE_ROUTE}/${it.title}"
                    )
                } else {
                    it.toSupportMessageResponse()
                }
            }
        }

    suspend fun delete(
        request: IdIpDTO
    ) {
        dbQuery {

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            if (
                existing.messageType ==
                SupportMessageType.Image
            ) {
                existing.title?.let {
                    imageController.deleteImage(
                        title = it,
                        imageType = ImageType.Support
                    )
                }
            }

            repository.delete(
                request.id
            )
        }
    }

    private suspend fun broadcast(
        message: SupportMessageResponse
    ) {

        val payload =
            Json.encodeToString(
                message
            )

        SupportSessionManager
            .getSessions(
                message.supportChatId
            )
            .forEach {

                runCatching {
                    it.send(
                        Frame.Text(
                            payload
                        )
                    )
                }
            }
    }
}