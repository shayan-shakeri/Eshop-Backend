package com.shayan.feature.category.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.image_controller.ImageController
import com.shayan.core.image_controller.ImageType
import com.shayan.feature.category.constants.CategoryConst
import com.shayan.feature.category.dto.CategoryResponse
import com.shayan.feature.category.mapper.toCategoryResponse
import com.shayan.feature.category.model.Category
import com.shayan.feature.category.repository.CategoryRepository
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.user_pic.constants.UserPicConst
import com.shayan.feature.user_pic.mapper.toUserPicResponse
import core.database.dbQuery
import core.util.IdGenerator

class CategoryService (
    private val categoryRepository: CategoryRepository,
    private val employeeAuditLogService: EmployeeAuditLogService,
    private val imageController: ImageController
){

    suspend fun addCategory(
        employeeId: String,
        roleId: String,
        ip: String,
        categoryName: String,
        fileBytes: ByteArray,
        baseUrl: String
    ): CategoryResponse{
        runCatching { employeeAuditLogService.addAuditLog(employeeId, roleId, CategoryConst.ADD_ACTION, ip) }
        return dbQuery {
            val fileName = imageController.addImage(
                fileBytes = fileBytes,
                title = "$categoryName.png",
                imageType = ImageType.CategoryImage
            )

            val category = Category(
                id = IdGenerator.generate(),
                name = categoryName,
            )

            categoryRepository.addCategory(category)
                ?.toCategoryResponse(
                    "$baseUrl${CategoryConst.IMAGE_ROUTE}/${fileName}"
                )
                ?: throw FailedToAdd()
        }
    }

    suspend fun readCategory(baseUrl: String): List<CategoryResponse> = dbQuery{
        categoryRepository.getAllCategories().map {
            it.toCategoryResponse("$baseUrl${CategoryConst.IMAGE_ROUTE}/${it.name}.png")
        }
    }
}