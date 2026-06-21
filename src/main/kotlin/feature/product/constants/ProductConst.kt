package com.shayan.feature.product.constants

object ProductConst {

    const val TABLE_NAME = "product"

    const val ID = "id"
    const val CATEGORY_ID = "category_id"
    const val FILTER_ID = "filter_id"

    const val NAME = "name"
    const val DESCRIPTION = "description"

    const val ORIGINAL_PRICE = "original_price"
    const val PRICE = "price"

    const val STOCK = "stock"

    const val SIZE = "size"
    const val LENGTH = "length"
    const val MATERIAL = "material"

    const val GENDER = "gender"
    const val AGE = "age"

    // Lengths
    const val NAME_LENGTH = 255
    const val SIZE_LENGTH = 10
    const val LENGTH_LENGTH = 20
    const val MATERIAL_LENGTH = 40

    // Parameters
    const val ID_PARAM = "id"
    const val CATEGORY_ID_PARAM = "categoryId"
    const val FILTER_ID_PARAM = "filterId"
    const val IP_PARAM = "ip"
    const val NAME_PARAM = "name"

    // Parameter URLs
    private const val ID_PARAM_URL = "/{$ID_PARAM}"
    private const val CATEGORY_ID_PARAM_URL = "/{$CATEGORY_ID_PARAM}"
    private const val FILTER_ID_PARAM_URL = "/{$FILTER_ID_PARAM}"
    private const val IP_PARAM_URL = "/{$IP_PARAM}"
    private const val NAME_PARAM_URL = "/{$NAME_PARAM}"

    // Routes
    const val MAIN_ROUTE = "/product"

    const val ADD_ROUTE =
        "/add$IP_PARAM_URL"

    const val READ_ROUTE =
        "/read$ID_PARAM_URL"

    const val READ_ALL_ROUTE =
        "/read/all"

    const val READ_PREVIEW_ROUTE =
        "/read/preview"

    const val READ_CATEGORY_ROUTE =
        "/read/category$CATEGORY_ID_PARAM_URL"

    const val READ_FILTER_ROUTE =
        "/read/filter$FILTER_ID_PARAM_URL"

    const val SEARCH_ROUTE =
        "/search$NAME_PARAM_URL"

    const val UPDATE_ROUTE =
        "/update$ID_PARAM_URL$IP_PARAM_URL"

    const val DELETE_ROUTE =
        "/delete$ID_PARAM_URL$IP_PARAM_URL"

    const val INCREASE_STOCK_ROUTE =
        "/stock/increase$ID_PARAM_URL$IP_PARAM_URL"

    const val DECREASE_STOCK_ROUTE =
        "/stock/decrease$ID_PARAM_URL$IP_PARAM_URL"

    // Actions
    const val ADD_ACTION =
        "Added a product"

    const val UPDATE_ACTION =
        "Updated a product"

    const val DELETE_ACTION =
        "Deleted a product"

    const val INCREASE_STOCK_ACTION =
        "Increased product stock"

    const val DECREASE_STOCK_ACTION =
        "Decreased product stock"
}