package com.shayan.feature.search_history.constants

object SearchHistoryConstant {
    const val TABLE_NAME = "search_history"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val CONTENT = "content"
    const val CREATED_AT = "created_at"

    //-----------------numeral
    const val CONTENT_LENGTH = 255

    //----------------routing
    const val IP_PARM = "ip"
    const val MAIN_ROUTE = "/search-history"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read"
    const val DELETE_ROUTE = "/delete/{ip}"

    //----------------action
    const val DELETE_ACTION = "User delete search history"
}