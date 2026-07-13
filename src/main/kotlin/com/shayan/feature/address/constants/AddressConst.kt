package com.shayan.feature.address.constants

object AddressConst {
    const val TABLE_NAME = "address"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val COUNTRY = "country"
    const val STATE = "state"
    const val CITY = "city"
    const val ROAD = "road"
    const val POSTAL_CODE = "postal_code"
    const val ADDITIONAL_INFO = "additional_info"
    const val MAIN_ADDRESS = "main_address"

    //------------------numeral
    const val COUNTRY_LENGTH = 50
    const val STATE_LENGTH = 50
    const val CITY_LENGTH = 50
    const val ROAD_LENGTH = 50
    const val POSTAL_CODE_LENGTH = 20

    //------------------routes
    private const val DELETE_ROUTE = "/delete"
    private const val UPDATE_ROUTE = "/update"
    private const val IP_ROUTE = "{ip}"

    const val MAIN_ROUTE = "/address"
    const val IP_PARAM = "ip"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read/$IP_ROUTE"

    const val UPDATE_INFO_ROUTE = "$UPDATE_ROUTE/info"
    const val UPDATE_ACTIVE_ADDRESS_ROUTE = "$UPDATE_ROUTE/active_address"

    const val DELETE_SINGLE_ROUTE = "$DELETE_ROUTE/single"
    const val DELETE_ALL_ROUTE = "$DELETE_ROUTE/all/$IP_ROUTE"

    //-----------------actions
    const val ADD_ACTION = "User added a address"
    const val READ_ACTION = "User read all of his address"
    const val UPDATE_ACTION = "User updated a address"
    const val UPDATE_ACTIVE_ADDRESS_ACTION = "User updated a address"

    const val DELETE_SINGLE_ACTION= "User deleted a single address"
    const val DELETE_ALL_ACTION = "User deleted all addresses"
}