package com.shayan.feature.product_image.constants

object ProductImageConst {

    const val TABLE_NAME = "product_image"

    const val ID = "id"
    const val PRODUCT_ID_DB = "product_id"
    const val PREVIEW_IMAGE = "preview_image"
    const val TITLE = "title"

    const val TITLE_LENGTH = 40

    // Routes
    private const val IP_PARAM_URL = "/{ip}"
    private const val IMAGE_ID_PARAM = "/{imageId}"
    private const val PRODUCT_ID_PARAM = "/{productId}"

    const val MAIN_ROUTE = "/product-image"

    const val IMAGE_ROUTE = "/images/product"

    const val FILE_PATH = "images/product"
    const val REMOTE_PATH = "images/product"

    const val IP_PARAM = "ip"
    const val IMAGE_ID = "imageId"
    const val PRODUCT_ID = "productId"

    const val ADD_ROUTE = "/add/$PRODUCT_ID_PARAM/{preview}/$IP_PARAM_URL"

    const val READ_PREVIEW_ROUTE = "/preview/$PRODUCT_ID_PARAM"

    const val READ_ALL_ROUTE = "/all/$PRODUCT_ID_PARAM"

    const val UPDATE_IMAGE_ROUTE = "/update/image/$IMAGE_ID_PARAM/$IP_PARAM_URL"

    const val UPDATE_PREVIEW_ROUTE = "/update/preview/$IMAGE_ID_PARAM/$IP_PARAM_URL"

    const val DELETE_SINGLE_ROUTE = "/delete/$IMAGE_ID_PARAM/$IP_PARAM_URL"

    const val DELETE_ALL_ROUTE = "/delete/all/$PRODUCT_ID_PARAM/$IP_PARAM_URL"

    const val MISSING_FILE = "Image file is required"

    // Actions
    const val ADD_ACTION = "Added a product image"

    const val READ_PREVIEW_ACTION = "Read product preview image"

    const val READ_ALL_ACTION = "Read all product images"

    const val UPDATE_IMAGE_ACTION = "Updated product image"

    const val UPDATE_PREVIEW_ACTION = "Updated preview product image"

    const val DELETE_SINGLE_ACTION = "Deleted product image"

    const val DELETE_ALL_ACTION = "Deleted all product images"
}