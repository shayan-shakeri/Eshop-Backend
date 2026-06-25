package com.shayan.feature.notification.constants

object NotificationConst {

    const val TABLE_NAME = "notification"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val TITLE = "title"
    const val DATE = "date"
    const val CONTENT = "content"
    const val OPENED = "opened"

    // Numeral
    const val TITLE_LENGTH = 255
    const val CONTENT_LENGTH = 65535

    // Route
    const val MAIN_ROUTE = "/notification"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read"
    const val UPDATE_ROUTE = "/update"
    const val DELETE_ROUTE = "/delete"

    // Action
    const val ADD_ACTION =
        "Added notification"

    const val OPEN_ACTION =
        "Opened notification"

    const val DELETE_ACTION =
        "Deleted notification"
}