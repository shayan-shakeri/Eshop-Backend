package com.shayan.feature.role.constants

object RoleConst {
    const val TABLE_NAME = "role"
    const val ID = "id"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val CODE = "code"

    //------------numeral
    const val TITLE_LENGTH = 255

    //-----------route
    private const val ROLE_URL = "{role}"
    const val ROLE_PARAM = "role"
    const val MAIN_ROUTE = "/role"
    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read/$ROLE_URL"

    const val ADD_ACTION = "Added a new role to the database this action is not revertible"

}