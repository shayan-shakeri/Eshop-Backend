package com.shayan.feature.comment.constants

object CommentConst {

    const val TABLE_NAME = "comment"

    const val ID = "id"
    const val PRODUCT_ID = "product_id"
    const val USER_ID = "user_id"
    const val TITLE = "title"
    const val CONTENT = "content"
    const val RATING = "rating"
    const val PURCHASED = "purchased"
    const val CREATED_AT = "created_at"

    // Numerals
    const val TITLE_LENGTH = 255
    const val CONTENT_LENGTH = 255

    // Routes
    const val MAIN_ROUTE = "/comment"

    const val ADD_ROUTE = "/add"
    const val UPDATE_ROUTE = "/update"
    const val DELETE_ROUTE = "/delete"

    private const val PRODUCT_ID_ROUTE = "/{productId}"

    const val READ_ROUTE = "/read$PRODUCT_ID_ROUTE"

    // Params
    const val PRODUCT_ID_PARAM = "productId"

    // Actions
    const val ADD_ACTION =
        "User added a product comment"

    const val UPDATE_ACTION =
        "User updated a product comment"

    const val DELETE_ACTION =
        "User deleted a product comment"
}