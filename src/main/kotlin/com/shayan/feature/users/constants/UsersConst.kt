package com.shayan.feature.users.constants

object UsersConst {
    const val TABLE_NAME = "users"
    const val ID = "id"
    const val FULL_NAME = "full_name"
    const val EMAIL = "email"
    const val PHONE_NUMBER = "phone_number"
    const val GENDER = "gender"
    const val BIRTHDAY = "birthday"
    const val PASSWORD_HASH = "password_hash"
    const val ITERATIONS = "iterations"
    const val ALGORITHM = "algorithm"
    const val SALT = "salt"

    //-------------NUMERALS
    const val FULL_NAME_LENGTH = 100
    const val EMAIL_LENGTH = 255
    const val PHONE_LENGTH = 16
    const val PASSWORD_HASH_LENGTH = 128
    const val ALGORITHM_LENGTH = 64
    const val SALT_LENGTH = 64

    //-------------ROUTING
    private const val IP_PARAM_URL = "/{ip}"
    private const val LOGIN_ROUTE = "/login"
    private const val UPDATE_ROUTE = "/update"

    const val MAIN_ROUTE = "/user"
    const val IP_PARAM = "ip"

    const val LOGIN_NORMAL_ROUTE = "$LOGIN_ROUTE/normal"
    const val LOGIN_TOKEN_ROUTE = "$LOGIN_ROUTE/token/$IP_PARAM_URL"

    const val SIGNUP_ROUTE = "/signup"

    const val UPDATE_INFO_ROUTE = "$UPDATE_ROUTE/info"
    const val UPDATE_PASSWORD_ROUTE = "$UPDATE_ROUTE/password"

    const val DELETE_ROUTE = "/delete/$IP_PARAM_URL"
    const val LOGOUT_ROUTE = "/logout/$IP_PARAM_URL"

    //---------------ACTIONS
    const val LOGIN_ACTION = "User logged in account"
    const val LOGIN_TOKEN_ACTION = "User logged in account via access token"
    const val SIGNUP_ACTION = "User created a new account via signup"

    const val UPDATE_INFO_ACTION = "User updated a personal info"
    const val UPDATE_PASSWORD_ACTION = "User updated a password, token revoked since they need to reenter their account"

    const val DELETE_ACTION = "User deleted his account"
    const val LOGOUT_ACTION = "User logged out of his account, so token got revoked"

}