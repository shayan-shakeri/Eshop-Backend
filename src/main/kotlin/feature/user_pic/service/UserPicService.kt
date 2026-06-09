package com.shayan.feature.user_pic.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.user_pic.constants.UserPicConst
import com.shayan.feature.user_pic.dto.UserPicResponse
import com.shayan.feature.user_pic.mapper.toUserPicResponse
import com.shayan.feature.user_pic.model.UserPic
import com.shayan.feature.user_pic.repository.UserPicRepository
import core.database.dbQuery
import io.ktor.server.plugins.*
import java.util.*

class UserPicService(
    private val repository: UserPicRepository,
    private val auditLogService: AuditLogService,
    private val imageController: ImageController
) {

    suspend fun addUserPic(
        userId: String,
        ip: String,
        fileBytes: ByteArray,
        originalFileName: String?
    ): UserPicResponse {

        runCatching {
            auditLogService.add(userId, UserPicConst.ADD_ACTION, ip)
        }

        return dbQuery {

            val fileName = imageController.addImage(
                fileBytes = fileBytes,
                title = originalFileName,
                imageType = ImageType.UserImage
            )

            val userPic = UserPic(
                id = UUID.randomUUID().toString(),
                userId = userId,
                title = fileName
            )

            repository.addUserPic(userPic)
                ?.toUserPicResponse("${UserPicConst.IMAGE_ROUTE}/${userPic.title}")
                ?: throw FailedToAdd()
        }
    }

    suspend fun readUserPic(userId: String): UserPicResponse = dbQuery {

        val userPic = repository.getUserPic(userId)
            ?: throw NotFoundException()

        userPic.toUserPicResponse(userPic.title)
    }

    suspend fun updateUserPic(
        userId: String,
        ip: String,
        fileBytes: ByteArray,
        originalFileName: String?
    ): UserPicResponse {

        runCatching {
            auditLogService.add(userId, UserPicConst.UPDATE_ACTION, ip)
        }

        return dbQuery {

            val existing = repository.getUserPic(userId)
                ?: throw NotFoundException()

            val finalFileName = originalFileName ?: existing.title

            imageController.updateImage(
                image = fileBytes,
                title = finalFileName,
                imageType = ImageType.UserImage
            )

            val updatedUserPic = UserPic(
                id = existing.id,
                userId = existing.userId,
                title = finalFileName
            )

            repository.updateUserPic(updatedUserPic)
                ?.toUserPicResponse("${UserPicConst.IMAGE_ROUTE}/${updatedUserPic.title}")
                ?: throw NotFoundException()

        }
    }

    suspend fun deleteUserPic(userId: String, ip: String) {

        runCatching {
            auditLogService.add(userId, UserPicConst.DELETE_ACTION, ip)
        }

        dbQuery {

            val existing = repository.getUserPic(userId)
                ?: throw NotFoundException()

            val fileDeleted = imageController.deleteImage(
                title = existing.title,
                imageType = ImageType.UserImage
            )

            if (!fileDeleted) {
                throw NotFoundException()
            }

            repository.deleteUserPic(userId)
        }
    }
}