package com.shayan.feature.question.constants

object QuestionConst {

    const val TABLE_NAME = "question"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val PRODUCT_ID = "product_id"
    const val CONTENT = "content"

    // Numerals
    const val CONTENT_LENGTH = 255

    // Routes
    const val MAIN_ROUTE = "/question"

    const val ADD_ROUTE = "/add"
    const val UPDATE_ROUTE = "/update"

    private const val PRODUCT_ID_ROUTE = "/{productId}"

    const val READ_ROUTE = "/read$PRODUCT_ID_ROUTE"

    const val DELETE_ROUTE = "/delete"

    // Params
    const val PRODUCT_ID_PARAM = "productId"

    // Actions
    const val ADD_ACTION =
        "User asked a question about a product"

    const val UPDATE_ACTION =
        "User updated a question"

    const val DELETE_ACTION =
        "User deleted a question"
}