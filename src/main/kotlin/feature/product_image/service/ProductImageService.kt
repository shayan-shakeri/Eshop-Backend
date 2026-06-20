package com.shayan.feature.product_image.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.product_image.constants.ProductImageConst
import com.shayan.feature.product_image.dto.ProductImageResponse
import com.shayan.feature.product_image.mapper.toProductImageResponse
import com.shayan.feature.product_image.model.ProductImage
import com.shayan.feature.product_image.repository.ProductImageRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*

class ProductImageService(
    private val repository: ProductImageRepository,
    private val employeeAuditLogService: EmployeeAuditLogService,
    private val imageController: ImageController
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        productId: String,
        ip: String,
        fileBytes: ByteArray,
        originalFileName: String?,
        previewImage: Boolean,
        baseUrl: String
    ): ProductImageResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = ProductImageConst.ADD_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val existingPreview = repository.findPreview(productId)

            val shouldBePreview =
                previewImage || existingPreview == null

            if (shouldBePreview) {
                repository.clearPreview(productId)
            }

            val fileName = imageController.addImage(
                fileBytes = fileBytes,
                title = originalFileName,
                imageType = ImageType.ProductImage
            )

            val request = ProductImage(
                id = IdGenerator.generate(),
                productId = productId,
                previewImage = shouldBePreview,
                title = fileName
            )

            repository.add(request)
                ?.toProductImageResponse(
                    "$baseUrl${ProductImageConst.IMAGE_ROUTE}/${fileName}"
                )
                ?: throw FailedToAdd()
        }
    }

    suspend fun readPreview(
        productId: String,
        baseUrl: String
    ): ProductImageResponse =
        dbQuery {

            val result =  repository.findPreview(productId)
            result?.toProductImageResponse(
                "$baseUrl${ProductImageConst.IMAGE_ROUTE}/${result.title}"
            )
                ?: throw NotFoundException()
        }

    suspend fun readAll(
        productId: String,
        baseUrl: String
    ): List<ProductImageResponse> =
        dbQuery {

            repository.findAll(productId)
                .map {
                    it.toProductImageResponse(
                        "$baseUrl${ProductImageConst.IMAGE_ROUTE}/${it.title}"
                    )
                }
        }

    suspend fun updateImage(
        employeeId: String,
        roleId: String,
        imageId: String,
        ip: String,
        fileBytes: ByteArray,
        baseUrl: String
    ): ProductImageResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = ProductImageConst.UPDATE_IMAGE_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val existing =
                repository.findById(imageId)
                    ?: throw NotFoundException()

            imageController.updateImage(
                image = fileBytes,
                title = existing.title,
                imageType = ImageType.ProductImage
            )

            repository.update(existing)
                ?.toProductImageResponse(
                    "$baseUrl${ProductImageConst.IMAGE_ROUTE}/${existing.title}"
                )
                ?: throw NotFoundException()
        }
    }

    suspend fun updatePreview(
        employeeId: String,
        roleId: String,
        imageId: String,
        ip: String,
        baseUrl: String
    ): ProductImageResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = ProductImageConst.UPDATE_PREVIEW_ACTION,
                ip = ip
            )
        }

        return dbQuery {

            val image =
                repository.findById(imageId)
                    ?: throw NotFoundException()

            repository.clearPreview(image.productId)


            val result =  repository.setPreview(imageId)
            result?.toProductImageResponse(
                "$baseUrl${ProductImageConst.IMAGE_ROUTE}/${result.title}"
            )
                ?: throw NotFoundException()
        }
    }

    suspend fun deleteSingle(
        employeeId: String,
        roleId: String,
        imageId: String,
        ip: String
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = ProductImageConst.DELETE_SINGLE_ACTION,
                ip = ip
            )
        }

        dbQuery {

            val existing =
                repository.findById(imageId)
                    ?: throw NotFoundException()

            val deleted = imageController.deleteImage(
                title = existing.title,
                imageType = ImageType.ProductImage
            )

            if (!deleted) {
                throw NotFoundException()
            }

            repository.delete(imageId)

            if (existing.previewImage) {

                val remaining =
                    repository.findAll(existing.productId)

                if (remaining.isNotEmpty()) {
                    repository.setPreview(
                        remaining.first().id
                    )
                }
            }
        }
    }

    suspend fun deleteAll(
        employeeId: String,
        roleId: String,
        productId: String,
        ip: String
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = ProductImageConst.DELETE_ALL_ACTION,
                ip = ip
            )
        }

        dbQuery {

            repository.findAll(productId)
                .forEach {

                    imageController.deleteImage(
                        title = it.title,
                        imageType = ImageType.ProductImage
                    )
                }

            repository.deleteAll(productId)
        }
    }
}