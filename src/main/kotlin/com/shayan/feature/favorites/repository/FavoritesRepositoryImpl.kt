package com.shayan.feature.favorites.repository

import com.shayan.feature.favorites.mapper.toFavorite
import com.shayan.feature.favorites.model.Favorite
import com.shayan.feature.favorites.table.FavoritesTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class FavoritesRepositoryImpl :
    FavoritesRepository {

    override suspend fun add(
        favorite: Favorite
    ): Favorite? {

        FavoritesTable.insert {
            it[id] = favorite.id
            it[userId] = favorite.userId
            it[productId] = favorite.productId
        }

        return findById(
            favorite.id
        )
    }

    override suspend fun findById(
        id: String
    ): Favorite? =
        FavoritesTable
            .selectAll()
            .where {
                FavoritesTable.id eq id
            }
            .singleOrNull()
            ?.toFavorite()

    override suspend fun findByUser(
        userId: String
    ): List<Favorite> =
        FavoritesTable
            .selectAll()
            .where {
                FavoritesTable.userId eq userId
            }
            .map {
                it.toFavorite()
            }

    override suspend fun findByUserAndProduct(
        userId: String,
        productId: String
    ): Favorite? =
        FavoritesTable
            .selectAll()
            .where {
                (FavoritesTable.userId eq userId) and
                        (FavoritesTable.productId eq productId)
            }
            .singleOrNull()
            ?.toFavorite()

    override suspend fun delete(
        id: String
    ) {
        FavoritesTable.deleteWhere {
            FavoritesTable.id eq id
        }
    }
}