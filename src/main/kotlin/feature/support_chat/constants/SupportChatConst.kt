package com.shayan.feature.support_chat.constants

object SupportChatConst {

    const val TABLE_NAME = "support_chat"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val NAME = "name"
    const val PRIORITY = "priority"
    const val STATUS = "status"

    // numerical
    const val NAME_LENGTH = 255

    // params
    const val ID_PARAM = "id"
    const val STATUS_PARAM = "status"
    const val PRIORITY_PARAM = "priority"

    // routes
    const val MAIN_ROUTE = "/support-chat"

    const val ADD_ROUTE = "/add"

    const val READ_ROUTE = "/read"

    const val READ_SINGLE_ROUTE =
        "/read/{$ID_PARAM}"

    const val READ_ALL_ROUTE =
        "/read/all"

    const val READ_BY_STATUS_ROUTE =
        "/read/status/{$STATUS_PARAM}"

    const val READ_BY_PRIORITY_ROUTE =
        "/read/priority/{$PRIORITY_PARAM}"

    const val UPDATE_PRIORITY_ROUTE =
        "/update/priority"

    const val UPDATE_STATUS_ROUTE =
        "/update/status"

    const val DELETE_ROUTE =
        "/delete"

    // actions
    const val ADD_ACTION =
        "Added support chat"

    const val UPDATE_PRIORITY_ACTION =
        "Updated support chat priority"

    const val UPDATE_STATUS_ACTION =
        "Updated support chat status"

    const val DELETE_ACTION =
        "Deleted support chat"

    // errors
    const val MISSING_ID =
        "Support chat id is required"

    const val MISSING_PRIORITY =
        "Priority is required"

    const val MISSING_STATUS =
        "Status is required"
}