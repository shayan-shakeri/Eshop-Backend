package com.shayan.feature.filter.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.filter.constants.FilterConst
import com.shayan.feature.filter.dto.FilterResponse
import com.shayan.feature.filter.mapper.toFilterResponse
import com.shayan.feature.filter.model.Filter
import com.shayan.feature.filter.repository.RepositoryFilter
import core.database.dbQuery
import core.util.IdGenerator

class FilterService (
    private val filterRepository: RepositoryFilter,
    private val imageController: ImageController,
    private val employeeAuditLogService: EmployeeAuditLogService
){

    suspend fun add(
        employeeId: String,
        roleId: String,
        ip: String,
        filterName: String,
        categoryId: String,
        fileBytes: ByteArray,
        baseUrl: String,
        originalFilename: String?
    ): FilterResponse {
        runCatching { employeeAuditLogService.addAuditLog(employeeId, roleId, FilterConst.ADD_ACTION, ip) }
        return dbQuery{

            val fileName = imageController.addImage(
                fileBytes = fileBytes,
                title = originalFilename ,
                imageType = ImageType.FilterImage
            )
            val request = Filter(
                id = IdGenerator.generate(),
                categoryId = categoryId,
                name = filterName,
                imageTitle = fileName,
            )



            filterRepository.addFilter(request)
                ?.toFilterResponse(
                    "$baseUrl${FilterConst.IMAGE_ROUTE}/${fileName}"
                )
                ?: throw FailedToAdd()
        }
    }

    suspend fun read(baseUrl: String): List<FilterResponse> = dbQuery{
        filterRepository.readFilter().map { it.toFilterResponse("$baseUrl${FilterConst.IMAGE_ROUTE}/${it.imageTitle}") }
    }
}