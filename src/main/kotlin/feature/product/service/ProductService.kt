package com.shayan.feature.product.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.discount.service.DiscountService
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.product.constants.ProductConst
import com.shayan.feature.product.dto.AddProductRequest
import com.shayan.feature.product.dto.ProductPreviewResponse
import com.shayan.feature.product.dto.ProductResponse
import com.shayan.feature.product.dto.UpdateProductRequest
import com.shayan.feature.product.mapper.toProduct
import com.shayan.feature.product.mapper.toProductPreviewResponse
import com.shayan.feature.product.mapper.toProductResponse
import com.shayan.feature.product.model.Product
import com.shayan.feature.product.repository.ProductRepository
import com.shayan.feature.product_image.service.ProductImageService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import java.math.BigDecimal

class ProductService(
    private val repository: ProductRepository,
    private val employeeAuditLogService: EmployeeAuditLogService,
    private val discountService: DiscountService,
    private val productImageService: ProductImageService
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        ip: String,
        request: AddProductRequest
    ): ProductResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                ProductConst.ADD_ACTION,
                ip
            )
        }

        return dbQuery {

            val product = Product(
                id = IdGenerator.generate(),
                categoryId = request.categoryId,
                filterId = request.filterId,
                name = request.name,
                description = request.description,
                originalPrice = request.originalPrice,
                price = request.price,
                stock = request.stock,
                size = request.size,
                length = request.length,
                material = request.material,
                gender = request.gender,
                age = request.age
            )

            repository.add(product)
                ?.toProductResponse()
                ?: throw FailedToAdd()
        }
    }

    suspend fun read(id: String): ProductResponse =
        dbQuery {
            repository.findById(id)
                ?.toProductResponse()
                ?: throw NotFoundException()
        }

    suspend fun readAll(): List<ProductResponse> =
        dbQuery {
            repository.findAll().map { it.toProductResponse() }
        }

    suspend fun readByCategory(categoryId: String): List<ProductResponse> =
        dbQuery {
            repository.findByCategoryId(categoryId).map { it.toProductResponse() }
        }

    suspend fun readByFilter(filterId: String): List<ProductResponse> =
        dbQuery {
            repository.findByFilterId(filterId).map { it.toProductResponse() }
        }

    suspend fun searchByName(name: String): List<ProductResponse> =
        dbQuery {
            repository.searchByName(name).map { it.toProductResponse() }
        }

    suspend fun update(
        employeeId: String,
        roleId: String,
        id: String,
        ip: String,
        request: UpdateProductRequest
    ): ProductResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                ProductConst.UPDATE_ACTION,
                ip
            )
        }

        return dbQuery {

            val existing = repository.findById(id)
                ?: throw NotFoundException()

            val updated = Product(
                id = existing.id,
                categoryId = request.categoryId,
                filterId = request.filterId,
                name = request.name,
                description = request.description,
                originalPrice = request.originalPrice,
                price = request.price,
                stock = request.stock,
                size = request.size,
                length = request.length,
                material = request.material,
                gender = request.gender,
                age = request.age
            )

            repository.update(updated)
                ?.toProductResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun delete(
        employeeId: String,
        roleId: String,
        id: String,
        ip: String
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                ProductConst.DELETE_ACTION,
                ip
            )
        }

        dbQuery {
            repository.findById(id)
                ?: throw NotFoundException()

            repository.delete(id)
        }
    }

    suspend fun increaseStock(
        employeeId: String,
        roleId: String,
        id: String,
        amount: Int,
        ip: String
    ): ProductResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                ProductConst.INCREASE_STOCK_ACTION,
                ip
            )
        }

        return dbQuery {

            repository.increaseStock(id, amount)
                ?.toProductResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun decreaseStock(
        employeeId: String,
        roleId: String,
        id: String,
        amount: Int,
        ip: String
    ): ProductResponse {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                ProductConst.DECREASE_STOCK_ACTION,
                ip
            )
        }

        return dbQuery {

            repository.decreaseStock(id, amount)
                ?.toProductResponse()
                ?: throw NotFoundException()
        }
    }

    suspend fun readPreview(
        baseUrl: String
    ): List<ProductPreviewResponse> =
        dbQuery {

            repository.findAll()
                .filter { it.stock > 0 }
                .map { product ->

                    val imageUrl = runCatching {
                        productImageService.readPreviewImage(
                            product.id,
                            baseUrl = baseUrl
                        )
                    }.getOrNull()

                    val discount = runCatching {
                        discountService.calculateApplicableDiscount(
                            productId = product.id,
                            userId = null,
                            quantity = 1
                        )
                    }.getOrNull()

                    val discountPrice: BigDecimal? =
                        discount?.let {
                            product.price -
                                    product.price.multiply(
                                        BigDecimal(it.value)
                                    ).divide(BigDecimal(100))
                        }

                    product.toProductPreviewResponse(
                        previewImage = imageUrl?.imageUrl,
                        discountPrice = discountPrice
                    )
                }
        }
}