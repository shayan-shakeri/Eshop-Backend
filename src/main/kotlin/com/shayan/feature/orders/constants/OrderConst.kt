package com.shayan.feature.order.constants

object OrderConst {

    const val TABLE_NAME = "orders"

    const val ID = "id"
    const val USER_ID = "user_id"
    const val ADDRESS_ID = "address_id"
    const val DELIVERY_STATE = "delivery_state"
    const val DELIVERY_DATE = "delivery_date"
    const val ORIGINAL_PRICE = "original_price"
    const val FINAL_PRICE = "final_price"
    const val PAYMENT_ID = "payment_id"
    const val PORT = "port"
    const val CREATED_AT = "created_at"

    // Numeral
    const val PORT_LENGTH = 40

    // Route
    const val MAIN_ROUTE = "/order"

    const val ADD_ROUTE = "/add"
    const val READ_ROUTE = "/read"
    const val READ_SINGLE_ROUTE = "/read/{id}"
    const val UPDATE_ROUTE = "/update"
    const val DELETE_ROUTE = "/delete"

    // Params
    const val ID_PARAM = "id"

    // Actions
    const val ADD_ACTION = "Added order"

    const val UPDATE_ACTION = "Updated order delivery state"

    const val DELETE_ACTION = "Deleted order"
}