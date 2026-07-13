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

    // routes
    const val MAIN_ROUTE = "/support-chat"

    const val ADD_ROUTE = "/add"

    const val READ_ROUTE = "/read"

    const val READ_SINGLE_ROUTE =
        "/read/{$ID_PARAM}"

    const val READ_ALL_ROUTE =
        "/read/all"


    const val UPDATE_PRIORITY_ROUTE =
        "/update/priority"

    const val UPDATE_STATUS_ROUTE =
        "/update/status"

    const val DELETE_ROUTE =
        "/delete"

}