package com.shayan.feature.filter.constants

import com.shayan.feature.category.constants.CategoryConst

object FilterConst {
    const val TABLE_NAME = "filter"
    const val ID = "id"
    const val CATEGORY_ID = "category_id"
    const val NAME = "name"
    const val IMAGE_TITLE = "image_title"

    //---------------numeral
    const val NAME_LENGTH = 255
    const val IMAGE_TITLE_LENGTH = 40

    //---------------route
    const val MAIN_ROUTE = "/filter"
    const val IMAGE_ROUTE = "${MAIN_ROUTE}/images"
    const val READ_ROUTE = "/read"
    const val ADD_ROUTE = "/add"
    const val REMOTE_PATH = "${MAIN_ROUTE}/images"
    const val FILE_PATH = "uploads/filter"

    //----------------field name
    const val IMAGE = "image"
    const val FILTER_NAME = "filterName"
    const val CATEGORY_ID_ENTRY = "categoryId"
    const val IP = "ip"

    //-----------------action
    const val ADD_ACTION = "category"

    //-----------------error
    const val MISSING_FILE_ERROR = "Image file is required"
    const val CATEGORY_ID_NULL_ERROR = "Category Id is required"
    const val FILTER_NULL_ERROR = "Filter name is required"
    const val IP_NULL_ERROR = "IP is required"


}