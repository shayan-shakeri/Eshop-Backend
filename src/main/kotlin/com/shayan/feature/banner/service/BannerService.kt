package com.shayan.feature.banner.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.banner.constants.BannerConst
import com.shayan.feature.banner.dto.UpdateBannerRequest
import com.shayan.feature.banner.mapper.toBannerResponse
import com.shayan.feature.banner.model.Banner
import com.shayan.feature.banner.repository.BannerRepository
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.time.Instant

class BannerService(
    private val repository: BannerRepository,
    private val employeeAuditLogService: EmployeeAuditLogService,
    private val imageController: ImageController
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        fileBytes: ByteArray,
        originalFileName: String?,
        active: Boolean,
        ip: String,
        baseUrl: String
    ) =
        dbQuery {

            runCatching {
                employeeAuditLogService.addAuditLog(
                    employeeId = employeeId,
                    roleId = roleId,
                    action = BannerConst.ADD_ACTION,
                    ip = ip
                )
            }

            val fileName =
                imageController.addImage(
                    fileBytes = fileBytes,
                    title = originalFileName,
                    imageType = ImageType.Banner
                )

            val banner =
                Banner(
                    id = IdGenerator.generate(),
                    title = fileName,
                    active = active,
                    createdAt = Instant.now(),
                )

            repository.add(
                banner
            )?.toBannerResponse(
                "$baseUrl${BannerConst.IMAGE_ROUTE}/${fileName}"
            ) ?: throw FailedToAdd()
        }

    suspend fun read() =
        dbQuery {

            repository.readAll()
                .map {
                    it.toBannerResponse(
                        "${BannerConst.IMAGE_ROUTE}/${it.title}"
                    )
                }
        }

    suspend fun readActive() =
        dbQuery {

            repository.findActive()
                .map {
                    it.toBannerResponse(
                        "${BannerConst.IMAGE_ROUTE}/${it.title}"
                    )
                }
        }

    suspend fun update(
        employeeId: String,
        roleId: String,
        request: UpdateBannerRequest,
        baseUrl: String
    ) =
        dbQuery {

            runCatching {
                employeeAuditLogService.addAuditLog(
                    employeeId = employeeId,
                    roleId = roleId,
                    action = BannerConst.UPDATE_ACTION,
                    ip = request.ip
                )
            }

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            repository.update(
                existing.copy(
                    active = request.active
                )
            )?.toBannerResponse(
                "$baseUrl${BannerConst.IMAGE_ROUTE}/${existing.title}"
            ) ?: throw NotFoundException()
        }

    suspend fun delete(
        employeeId: String,
        roleId: String,
        request: IdIpDTO
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = BannerConst.DELETE_ACTION,
                ip = request.ip
            )
        }

        dbQuery {

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            imageController.deleteImage(
                title = existing.title,
                imageType = ImageType.Banner
            )

            repository.delete(
                request.id
            )
        }
    }
}