package com.shayan.feature.discount.repository

import com.shayan.feature.discount.model.Discount

interface DiscountRepository {

    suspend fun add(
        discount: Discount
    ): Discount?

    suspend fun findById(
        id: String
    ): Discount?

    suspend fun findAll(): List<Discount>

    suspend fun findByProductId(
        productId: String
    ): List<Discount>

    suspend fun findByUserId(
        userId: String
    ): List<Discount>

    suspend fun findActive(): List<Discount>

    suspend fun update(
        discount: Discount
    ): Discount?

    suspend fun activate(
        id: String
    ): Discount?

    suspend fun deactivate(
        id: String
    ): Discount?

    suspend fun delete(
        id: String
    )
}