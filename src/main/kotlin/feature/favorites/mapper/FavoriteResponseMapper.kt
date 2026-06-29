package com.shayan.feature.favorites.mapper

import com.shayan.feature.favorites.dto.FavoriteResponse
import com.shayan.feature.favorites.model.Favorite

fun Favorite.toFavoriteResponse() =
    FavoriteResponse(
        id = id,
        userId = userId,
        productId = productId
    )