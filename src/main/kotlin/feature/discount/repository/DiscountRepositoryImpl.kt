package com.shayan.feature.discount.repository

import com.shayan.feature.discount.mapper.toDiscount
import com.shayan.feature.discount.model.Discount
import com.shayan.feature.discount.table.DiscountTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DiscountRepositoryImpl : DiscountRepository {

    override suspend fun add(
        discount: Discount
    ): Discount? {

        DiscountTable.insert {

            it[id] = discount.id
            it[productId] = discount.productId
            it[userId] = discount.userId
            it[value] = discount.value
            it[quantity] = discount.quantity
            it[conditions] = discount.conditions
            it[conditionValue] = discount.conditionValue
            it[active] = discount.active
            it[endingDate] = discount.endingDate
        }

        return findById(discount.id)
    }

    override suspend fun findById(
        id: String
    ): Discount? =
        DiscountTable
            .selectAll()
            .where {
                DiscountTable.id eq id
            }
            .singleOrNull()
            ?.toDiscount()

    override suspend fun findAll(): List<Discount> =
        DiscountTable
            .selectAll()
            .map {
                it.toDiscount()
            }

    override suspend fun findByProductId(
        productId: String
    ): List<Discount> =
        DiscountTable
            .selectAll()
            .where {
                DiscountTable.productId eq productId
            }
            .map {
                it.toDiscount()
            }

    override suspend fun findByUserId(
        userId: String
    ): List<Discount> =
        DiscountTable
            .selectAll()
            .where {
                DiscountTable.userId eq userId
            }
            .map {
                it.toDiscount()
            }

    override suspend fun findActive(): List<Discount> =
        DiscountTable
            .selectAll()
            .where {
                DiscountTable.active eq true
            }
            .map {
                it.toDiscount()
            }

    override suspend fun update(
        discount: Discount
    ): Discount? {

        DiscountTable.update(
            where = {
                DiscountTable.id eq discount.id
            }
        ) {

            it[userId] = discount.userId
            it[value] = discount.value
            it[quantity] = discount.quantity
            it[conditions] = discount.conditions
            it[conditionValue] = discount.conditionValue
            it[active] = discount.active
            it[endingDate] = discount.endingDate
        }

        return findById(discount.id)
    }

    override suspend fun activate(
        id: String
    ): Discount? {

        DiscountTable.update(
            where = {
                DiscountTable.id eq id
            }
        ) {

            it[active] = true
        }

        return findById(id)
    }

    override suspend fun deactivate(
        id: String
    ): Discount? {

        DiscountTable.update(
            where = {
                DiscountTable.id eq id
            }
        ) {

            it[active] = false
        }

        return findById(id)
    }

    override suspend fun delete(
        id: String
    ) {

        DiscountTable.deleteWhere {
            DiscountTable.id eq id
        }
    }
}