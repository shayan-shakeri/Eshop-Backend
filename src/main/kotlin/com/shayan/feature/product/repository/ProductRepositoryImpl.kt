package com.shayan.feature.product.repository

import com.shayan.feature.product.mapper.toProduct
import com.shayan.feature.product.model.Product
import com.shayan.feature.product.table.ProductTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.minus
import org.jetbrains.exposed.sql.SqlExpressionBuilder.plus

class ProductRepositoryImpl : ProductRepository {

    override suspend fun add(
        product: Product
    ): Product? {

        ProductTable.insert {

            it[id] = product.id
            it[categoryId] = product.categoryId
            it[filterId] = product.filterId
            it[name] = product.name
            it[description] = product.description
            it[originalPrice] = product.originalPrice
            it[price] = product.price
            it[stock] = product.stock
            it[size] = product.size
            it[length] = product.length
            it[material] = product.material
            it[gender] = product.gender
            it[age] = product.age
        }

        return findById(product.id)
    }

    override suspend fun findById(
        id: String
    ): Product? =
        ProductTable
            .selectAll()
            .where {
                ProductTable.id eq id
            }
            .singleOrNull()
            ?.toProduct()

    override suspend fun findAll(): List<Product> =
        ProductTable
            .selectAll()
            .map {
                it.toProduct()
            }

    override suspend fun findByCategoryId(
        categoryId: String
    ): List<Product> =
        ProductTable
            .selectAll()
            .where {
                ProductTable.categoryId eq categoryId
            }
            .map {
                it.toProduct()
            }

    override suspend fun findByFilterId(
        filterId: String
    ): List<Product> =
        ProductTable
            .selectAll()
            .where {
                ProductTable.filterId eq filterId
            }
            .map {
                it.toProduct()
            }

    override suspend fun searchByName(
        name: String
    ): List<Product> =
        ProductTable
            .selectAll()
            .where {
                ProductTable.name like "%$name%"
            }
            .map {
                it.toProduct()
            }

    override suspend fun update(
        product: Product
    ): Product? {

        ProductTable.update(
            where = {
                ProductTable.id eq product.id
            }
        ) {

            it[categoryId] = product.categoryId
            it[filterId] = product.filterId
            it[name] = product.name
            it[description] = product.description
            it[originalPrice] = product.originalPrice
            it[price] = product.price
            it[stock] = product.stock
            it[size] = product.size
            it[length] = product.length
            it[material] = product.material
            it[gender] = product.gender
            it[age] = product.age
        }

        return findById(product.id)
    }

    override suspend fun delete(
        id: String
    ) {

        ProductTable.deleteWhere {
            ProductTable.id eq id
        }
    }

    override suspend fun increaseStock(
        id: String,
        amount: Int
    ): Product? {

        ProductTable.update(
            where = {
                ProductTable.id eq id
            }
        ) {

            with(SqlExpressionBuilder) {
                it.update(
                    ProductTable.stock,
                    ProductTable.stock + amount
                )
            }
        }

        return findById(id)
    }

    override suspend fun decreaseStock(
        id: String,
        amount: Int
    ): Product? {

        ProductTable.update(
            where = {
                ProductTable.id eq id
            }
        ) {

            with(SqlExpressionBuilder) {
                it.update(
                    ProductTable.stock,
                    ProductTable.stock - amount
                )
            }
        }

        return findById(id)
    }
}