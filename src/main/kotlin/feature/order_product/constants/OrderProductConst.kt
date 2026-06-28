package com.shayan.feature.order_product.constants

object OrderProductConst {

    const val TABLE_NAME = "order_product"

    const val ID = "id"
    const val ORDER_ID = "order_id"
    const val PRODUCT_ID = "product_id"
    const val QUANTITY = "quantity"
    const val ORIGINAL_PRICE = "original_price"
    const val FINAL_PRICE = "final_price"

    // route
    const val MAIN_ROUTE = "/order-product"

    const val ADD_ROUTE = "/add"
    const val READ_BY_ORDER_ROUTE = "/read/order/{orderId}"
    const val READ_BY_PRODUCT_ROUTE = "/read/product/{productId}"
    const val DELETE_ROUTE = "/delete"

    // actions
    const val ADD_ACTION = "Added order product"
    const val DELETE_ACTION = "Deleted order product"
}