package com.shayan.core.image_controller

import com.shayan.core.exception.ImageExist
import core.util.IdGenerator
import java.io.File
import kotlinx.io.files.FileNotFoundException

class ImageController {

    private fun ImageType.dir(): File =
        when (this) {
            ImageType.UserImage -> ImageControllerConst.USER_IMAGE_PATH
            ImageType.ProductImage -> ImageControllerConst.PRODUCT_IMAGE_PATH
            ImageType.BannerImage -> ImageControllerConst.BANNER_IMAGE_PATH
            ImageType.CategoryImage -> ImageControllerConst.CATEGORY_IMAGE_PATH
            ImageType.FilterImage -> ImageControllerConst.FILE_IMAGE_PATH
        }

    private fun file(imageType: ImageType, title: String): File =
        File(imageType.dir(), title)

    private fun ensureDir(imageType: ImageType) {
        val dir = imageType.dir()
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }

    private fun exists(imageType: ImageType, title: String): Boolean =
        file(imageType, title).isFile


    fun addImage(
        fileBytes: ByteArray,
        title: String?,
        imageType: ImageType
    ): String {
        val fileName = title ?: "${IdGenerator.generate()}.png"

        if (exists(imageType, fileName)) {
            throw ImageExist()
        }

        ensureDir(imageType)

        file(imageType, fileName).writeBytes(fileBytes)

        return fileName
    }


    fun readImage(
        title: String,
        imageType: ImageType
    ): ByteArray {
        val file = file(imageType, title)

        if (!file.isFile) {
            throw FileNotFoundException("${ImageControllerConst.IMAGE_NOT_EXIST} $title")
        }

        return file.readBytes()
    }

    fun updateImage(
        image: ByteArray,
        title: String,
        imageType: ImageType
    ): String {
        ensureDir(imageType)

        val file = file(imageType, title)

        if (!file.isFile) {
            throw FileNotFoundException("${ImageControllerConst.IMAGE_NOT_EXIST} $title")
        }

        file.writeBytes(image)

        return title
    }

    fun deleteImage(
        title: String,
        imageType: ImageType
    ): Boolean {
        val file = file(imageType, title)
        return file.isFile && file.delete()
    }
}