package com.shayan.feature.favorites.table

import com.shayan.feature.favorites.constants.FavoritesConst
import com.shayan.feature.product.table.ProductTable
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object FavoritesTable : Table(
    FavoritesConst.TABLE_NAME
) {

    val id =
        varchar(
            FavoritesConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            FavoritesConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id,
            onDelete = ReferenceOption.CASCADE,
            onUpdate = ReferenceOption.RESTRICT
        )

    val productId =
        varchar(
            FavoritesConst.PRODUCT_ID,
            ANC.ID_LENGTH
        ).references(
            ProductTable.id,
            onDelete = ReferenceOption.CASCADE,
            onUpdate = ReferenceOption.RESTRICT
        )

    override val primaryKey =
        PrimaryKey(id)
}