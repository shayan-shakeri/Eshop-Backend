package com.shayan.feature.product_image.repository

import com.shayan.feature.product_image.model.ProductImage
import com.shayan.feature.product_image.table.ProductImageTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProductImageRepositoryImpl : ProductImageRepository {

    private fun ResultRow.toProductImage() =
        ProductImage(
            id = this[ProductImageTable.id],
            productId = this[ProductImageTable.productId],
            previewImage = this[ProductImageTable.previewImage],
            title = this[ProductImageTable.title]
        )

    override suspend fun add(
        productImage: ProductImage
    ): ProductImage? {

        ProductImageTable.insert {
            it[id] = productImage.id
            it[productId] = productImage.productId
            it[previewImage] = productImage.previewImage
            it[title] = productImage.title
        }

        return findById(productImage.id)
    }

    override suspend fun findById(
        id: String
    ): ProductImage? =
        ProductImageTable
            .selectAll()
            .where {
                ProductImageTable.id eq id
            }
            .singleOrNull()
            ?.toProductImage()

    override suspend fun findPreview(
        productId: String
    ): ProductImage? =
        ProductImageTable
            .selectAll()
            .where {
                (ProductImageTable.productId eq productId) and
                        (ProductImageTable.previewImage eq true)
            }
            .singleOrNull()
            ?.toProductImage()

    override suspend fun findAll(
        productId: String
    ): List<ProductImage> =
        ProductImageTable
            .selectAll()
            .where {
                ProductImageTable.productId eq productId
            }
            .map {
                it.toProductImage()
            }

    override suspend fun clearPreview(
        productId: String
    ) {
        ProductImageTable.update(
            where = {
                ProductImageTable.productId eq productId
            }
        ) {
            it[previewImage] = false
        }
    }

    override suspend fun setPreview(
        imageId: String
    ): ProductImage? {

        ProductImageTable.update(
            where = {
                ProductImageTable.id eq imageId
            }
        ) {
            it[previewImage] = true
        }

        return findById(imageId)
    }

    override suspend fun update(
        productImage: ProductImage
    ): ProductImage? {

        ProductImageTable.update(
            where = {
                ProductImageTable.id eq productImage.id
            }
        ) {
            it[productId] = productImage.productId
            it[previewImage] = productImage.previewImage
            it[title] = productImage.title
        }

        return findById(productImage.id)
    }

    override suspend fun delete(
        id: String
    ) {
        ProductImageTable.deleteWhere {
            ProductImageTable.id eq id
        }
    }

    override suspend fun deleteAll(
        productId: String
    ) {
        ProductImageTable.deleteWhere {
            ProductImageTable.productId eq productId
        }
    }
}