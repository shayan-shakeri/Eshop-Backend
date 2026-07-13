package com.shayan.feature.error_log.constants

object ErrorLogConst {

    const val TABLE_NAME = "error_log"

    const val ID = "id"
    const val ERROR_MESSAGE = "error_message"
    const val CREATED_AT = "created_at"

    // routes
    const val MAIN_ROUTE = "/error-log"

    const val READ_ROUTE = "/read"

    const val DELETE_ROUTE = "/delete/single/id"
    const val DELETE_ALL_ROUTE = "/delete/all"

    // actions
    const val DELETE_ALL_ACTION =
        "Deleted all error log"

    const val DELETE_ACTION =
        "Deleted error log"
}