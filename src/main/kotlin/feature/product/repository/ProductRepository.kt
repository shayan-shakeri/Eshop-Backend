package com.shayan.feature.product.repository

import com.shayan.feature.product.model.Product

interface ProductRepository {

    suspend fun add(
        product: Product
    ): Product?

    suspend fun findById(
        id: String
    ): Product?

    suspend fun findAll(): List<Product>

    suspend fun findByCategoryId(
        categoryId: String
    ): List<Product>

    suspend fun findByFilterId(
        filterId: String
    ): List<Product>

    suspend fun searchByName(
        name: String
    ): List<Product>

    suspend fun update(
        product: Product
    ): Product?

    suspend fun delete(
        id: String
    )

    suspend fun increaseStock(
        id: String,
        amount: Int
    ): Product?

    suspend fun decreaseStock(
        id: String,
        amount: Int
    ): Product?
}