package com.shayan.feature.favorites.repository

import com.shayan.feature.favorites.model.Favorite

interface FavoritesRepository {

    suspend fun add(
        favorite: Favorite
    ): Favorite?

    suspend fun findById(
        id: String
    ): Favorite?

    suspend fun findByUser(
        userId: String
    ): List<Favorite>

    suspend fun findByUserAndProduct(
        userId: String,
        productId: String
    ): Favorite?

    suspend fun delete(
        id: String
    )
}