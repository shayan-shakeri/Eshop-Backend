package com.shayan.feature.setting.constants

object SettingConst {

    const val TABLE_NAME = "setting"

    const val ID = "id"
    const val PRODUCT_ID = "product_id"
    const val NAME = "name"
    const val VALUE = "value"

    // Numerals
    const val NAME_LENGTH = 40
    const val VALUE_LENGTH = 255

    // Routes
    private const val ID_PARAM_URL = "/{id}"
    private const val PRODUCT_ID_PARAM_URL = "/{productId}"
    private const val IP_PARAM_URL = "/{ip}"

    const val MAIN_ROUTE = "/setting"

    const val ID_PARAM = "id"
    const val PRODUCT_ID_PARAM = "productId"
    const val IP_PARAM = "ip"

    const val ADD_ROUTE =
        "/add/$IP_PARAM_URL"

    const val READ_ROUTE =
        "/read/$ID_PARAM_URL"

    const val READ_ALL_ROUTE =
        "/read/all"

    const val READ_PRODUCT_ROUTE =
        "/read/product/$PRODUCT_ID_PARAM_URL"

    const val UPDATE_ROUTE =
        "/update/$ID_PARAM_URL/$IP_PARAM_URL"

    const val DELETE_ROUTE =
        "/delete/$ID_PARAM_URL/$IP_PARAM_URL"

    // Actions
    const val ADD_ACTION =
        "Added a product setting"

    const val UPDATE_ACTION =
        "Updated a product setting"

    const val DELETE_ACTION =
        "Deleted a product setting"
}