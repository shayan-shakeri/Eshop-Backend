package com.shayan.feature.user_pic.constants

object UserPicConst {
    const val TABLE_NAME = "user_pic"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val TITLE = "title"

    //-----------numeral
    const val TITLE_LENGTH = 40

    //-----------route
    private const val IP_URL = "{ip}"

    const val IP_PARAM = "ip"
    const val MAIN_ROUTE = "/user-pic"
    const val IMAGE_ROUTE = "$MAIN_ROUTE/images"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read/$IP_URL"
    const val UPDATE_ROUTE = "/update/$IP_URL"
    const val DELETE_ROUTE = "/delete/$IP_URL"

    //-----------actions
    const val ADD_ACTION = "User added a picture to his/her account"
    const val UPDATE_ACTION = "User updated his/her profile picture"
    const val DELETE_ACTION = "User deleted his/her profile picture"

    const val MISSING_FILE = "File missing"
}