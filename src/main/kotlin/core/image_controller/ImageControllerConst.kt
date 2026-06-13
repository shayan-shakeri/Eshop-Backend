package com.shayan.core.image_controller

import java.io.File

object ImageControllerConst {

    private const val MAIN_PATH = "uploads"

    val BASE_DIR: File = File(MAIN_PATH)

    val USER_IMAGE_PATH: File = File(BASE_DIR, "user")
    val PRODUCT_IMAGE_PATH: File = File(BASE_DIR, "products")
    val BANNER_IMAGE_PATH: File = File(BASE_DIR, "banners")
    val CATEGORY_IMAGE_PATH: File = File(BASE_DIR, "category")

    const val IMAGE_NOT_EXIST = "Image not found:"
}