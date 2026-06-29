package com.shayan.feature.favorites.mapper

import com.shayan.feature.favorites.model.Favorite
import com.shayan.feature.favorites.table.FavoritesTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toFavorite() =
    Favorite(
        id = this[FavoritesTable.id],
        userId = this[FavoritesTable.userId],
        productId = this[FavoritesTable.productId]
    )