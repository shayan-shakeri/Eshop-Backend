package com.shayan.feature.version_control.constants

object VersionControlConst {

    const val TABLE_NAME = "version_control"

    const val ID = "id"
    const val VERSION = "version"
    const val ACTIVE = "active"
    const val DESCRIPTION = "description"
    const val FORCED = "forced"
    const val CREATED_AT = "created_at"

    // lengths
    const val VERSION_LENGTH = 40

    // routes
    const val MAIN_ROUTE = "/version-control"

    const val ADD_ROUTE = "/add"

    const val READ_ROUTE = "/read"

    const val VERIFY_ROUTE = "/verify"

    const val UPDATE_ROUTE = "/update"

    const val DELETE_ROUTE = "/delete"

    // actions
    const val ADD_ACTION =
        "Added application version"

    const val UPDATE_ACTION =
        "Updated application version"

    const val DELETE_ACTION =
        "Deleted application version"

    // errors
    const val VERSION_REQUIRED =
        "Version is required"
}