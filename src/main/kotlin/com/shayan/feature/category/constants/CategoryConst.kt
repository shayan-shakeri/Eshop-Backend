package com.shayan.feature.category.constants

object CategoryConst {

    const val TABLE_NAME = "category"
    const val ID = "id"
    const val NAME = "name"
    const val IMAGE_TITLE = "image_title"

    const val NAME_LENGTH = 255
    const val IMAGE_TITLE_LENGTH = 255

    const val MAIN_ROUTE = "/category"
    const val IMAGE_ROUTE = "$MAIN_ROUTE/images"
    const val ADD_ROUTE = "/add"
    const val GET_ROUTE = "/read"


    const val REMOTE_PATH = "/images"
    const val FILE_PATH = "uploads/category"


    const val IMAGE = "image"
    const val CATEGORY_NAME = "categoryName"
    const val IP = "ip"


    const val ADD_ACTION = "Employee added a new category"


    const val MISSING_FILE_ERROR = "Image file is required"
    const val CATEGORY_NULL_ERROR = "Category name is required"
    const val IP_NULL_ERROR = "IP is required"
}