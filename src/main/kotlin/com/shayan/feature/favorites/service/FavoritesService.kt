package com.shayan.feature.favorites.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.favorites.dto.AddFavoriteRequest
import com.shayan.feature.favorites.dto.FavoriteResponse
import com.shayan.feature.favorites.mapper.toFavoriteResponse
import com.shayan.feature.favorites.model.Favorite
import com.shayan.feature.favorites.repository.FavoritesRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException

class FavoritesService(
    private val repository: FavoritesRepository
) {

    suspend fun add(
        userId: String,
        request: AddFavoriteRequest
    ): FavoriteResponse =
        dbQuery {

            val existing =
                repository.findByUserAndProduct(
                    userId,
                    request.productId
                )

            if (existing != null) {
                repository.delete(
                    existing.id
                )

                return@dbQuery existing
                    .toFavoriteResponse()
            }

            val favorite =
                Favorite(
                    id = IdGenerator.generate(),
                    userId = userId,
                    productId = request.productId
                )

            repository.add(
                favorite
            )?.toFavoriteResponse()
                ?: throw FailedToAdd()
        }

    suspend fun read(
        userId: String
    ): List<FavoriteResponse> =
        dbQuery {

            repository.findByUser(
                userId
            ).map {
                it.toFavoriteResponse()
            }
        }

    suspend fun delete(
        request: IdIpDTO
    ) {
        dbQuery {

            repository.findById(
                request.id
            ) ?: throw NotFoundException()

            repository.delete(
                request.id
            )
        }
    }
}