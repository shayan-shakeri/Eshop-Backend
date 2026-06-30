package com.shayan.feature.banner.constants

object BannerConst {

    const val TABLE_NAME = "banner"

    const val ID = "id"
    const val TITLE = "title"
    const val ACTIVE = "active"
    const val CREATED_AT = "created_at"

    //-------numerical
    const val TITLE_LENGTH = 40

    //-------routes
    const val MAIN_ROUTE = "/banner"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read"
    const val READ_ACTIVE_ROUTE = "/active"
    const val UPDATE_ROUTE = "/update"
    const val DELETE_ROUTE = "/delete"

    //-------request fields
    const val ACTIVE_PARAM = "active"
    const val IP = "ip"

    //-------image
    const val FILE_PATH = "uploads/banners"
    const val REMOTE_PATH = "/image"
    const val IMAGE_ROUTE = "$MAIN_ROUTE$REMOTE_PATH"

    //-------actions
    const val ADD_ACTION =
        "Added banner"

    const val UPDATE_ACTION =
        "Updated banner"

    const val DELETE_ACTION =
        "Deleted banner"

    //-------errors
    const val MISSING_FILE =
        "Image file is required"

    const val MISSING_ACTIVE =
        "Active is required"

    const val MISSING_IP =
        "Ip is required"
}