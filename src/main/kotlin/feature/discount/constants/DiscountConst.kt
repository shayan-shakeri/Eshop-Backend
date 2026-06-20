package com.shayan.feature.discount.constants

object DiscountConst {

    const val TABLE_NAME = "discount"

    const val ID = "id"
    const val PRODUCT_ID = "product_id"
    const val USER_ID = "user_id"
    const val VALUE = "value"
    const val QUANTITY = "quantity"
    const val CONDITIONS = "conditions"
    const val CONDITION_VALUE = "condition_value"
    const val ACTIVE = "active"
    const val ENDING_DATE = "ending_date"

    // Numerals
    const val CONDITION_LENGTH = 20

    // Routes
    private const val ID_PARAM_URL = "/{id}"
    private const val PRODUCT_ID_PARAM_URL = "/{productId}"
    private const val USER_ID_PARAM_URL = "/{userId}"
    private const val IP_PARAM_URL = "/{ip}"

    const val MAIN_ROUTE = "/discount"

    const val ID_PARAM = "id"
    const val PRODUCT_ID_PARAM = "productId"
    const val USER_ID_PARAM = "userId"
    const val IP_PARAM = "ip"

    const val ADD_ROUTE = "/add/$IP_PARAM_URL"

    const val READ_ROUTE = "/read/$ID_PARAM_URL"

    const val READ_ALL_ROUTE = "/read/all"

    const val READ_PRODUCT_ROUTE =
        "/read/product/$PRODUCT_ID_PARAM_URL"

    const val READ_USER_ROUTE =
        "/read/user/$USER_ID_PARAM_URL"

    const val READ_ACTIVE_ROUTE =
        "/read/active"

    const val UPDATE_ROUTE =
        "/update/$ID_PARAM_URL/$IP_PARAM_URL"

    const val ACTIVATE_ROUTE =
        "/activate/$ID_PARAM_URL/$IP_PARAM_URL"

    const val DEACTIVATE_ROUTE =
        "/deactivate/$ID_PARAM_URL/$IP_PARAM_URL"

    const val DELETE_ROUTE =
        "/delete/$ID_PARAM_URL/$IP_PARAM_URL"

    // Actions
    const val ADD_ACTION =
        "Added a discount"

    const val UPDATE_ACTION =
        "Updated a discount"

    const val ACTIVATE_ACTION =
        "Activated a discount"

    const val DEACTIVATE_ACTION =
        "Deactivated a discount"

    const val DELETE_ACTION =
        "Deleted a discount"
}