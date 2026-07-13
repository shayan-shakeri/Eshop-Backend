package com.shayan.feature.favorites.constants

object FavoritesConst {

    const val TABLE_NAME = "favorites"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val PRODUCT_ID = "product_id"

    // routes
    const val MAIN_ROUTE = "/favorites"

    const val ADD_ROUTE = "/add"

    const val READ_ROUTE = "/read"

    const val DELETE_ROUTE = "/delete"

    // actions
    const val ADD_ACTION =
        "Added product to favorites"

    const val DELETE_ACTION =
        "Removed product from favorites"

}