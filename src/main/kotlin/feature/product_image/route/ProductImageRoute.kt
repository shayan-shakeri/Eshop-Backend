package com.shayan.feature.product_image.route

import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract

import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.auth.authenticate
import io.ktor.server.http.content.staticFiles
import io.ktor.server.plugins.origin
import io.ktor.server.request.host
import io.ktor.server.request.port
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import java.io.File

fun Route.productImageRoute(
    productImageService: ProductImageService
) {

    route(`SettingConst.kt`.MAIN_ROUTE) {

        staticFiles(
            remotePath = `SettingConst.kt`.REMOTE_PATH,
            dir = File(`SettingConst.kt`.FILE_PATH)
        )

        authenticate(CJWT.ACCESS_AUTH) {

            post(`SettingConst.kt`.ADD_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                val productId =
                    call.parameters[`SettingConst.kt`.PRODUCT_ID]
                        ?: return@post call.respond(
                            HttpStatusCode.BadRequest
                        )

                val previewImage =
                    call.parameters["preview"]?.toBoolean()
                        ?: false

                val ip =
                    call.extractFromParam(
                        `SettingConst.kt`.IP_PARAM
                    )

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var fileName: String? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            fileBytes =
                                part.streamProvider().readBytes()

                            fileName =
                                part.originalFileName
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(
                        `SettingConst.kt`.MISSING_FILE,
                        status = HttpStatusCode.BadRequest
                    )
                }

                val result =
                    productImageService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        productId = productId,
                        ip = ip,
                        fileBytes = fileBytes!!,
                        originalFileName = fileName,
                        previewImage = previewImage,
                        baseUrl = baseUrl
                    )

                call.respond(result)
            }

            get(`SettingConst.kt`.READ_PREVIEW_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val productId =
                    call.parameters[`SettingConst.kt`.PRODUCT_ID]
                        ?: return@get call.respond(
                            HttpStatusCode.BadRequest
                        )

                call.respond(
                    productImageService.readPreview(
                        productId = productId,
                        baseUrl = baseUrl
                    )
                )
            }

            get(`SettingConst.kt`.READ_ALL_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val productId =
                    call.parameters[`SettingConst.kt`.PRODUCT_ID]
                        ?: return@get call.respond(
                            HttpStatusCode.BadRequest
                        )

                call.respond(
                    productImageService.readAll(
                        productId = productId,
                        baseUrl = baseUrl
                    )
                )
            }

            put(`SettingConst.kt`.UPDATE_IMAGE_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                val imageId =
                    call.parameters[`SettingConst.kt`.IMAGE_ID]
                        ?: return@put call.respond(
                            HttpStatusCode.BadRequest
                        )

                val ip =
                    call.extractFromParam(
                        `SettingConst.kt`.IP_PARAM
                    )

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            fileBytes =
                                part.streamProvider().readBytes()
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@put call.respondText(
                        `SettingConst.kt`.MISSING_FILE,
                        status = HttpStatusCode.BadRequest
                    )
                }

                val result =
                    productImageService.updateImage(
                        employeeId = employeeId,
                        roleId = roleId,
                        imageId = imageId,
                        ip = ip,
                        fileBytes = fileBytes!!,
                        baseUrl = baseUrl
                    )

                call.respond(result)
            }

            put(`SettingConst.kt`.UPDATE_PREVIEW_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                val imageId =
                    call.parameters[`SettingConst.kt`.IMAGE_ID]
                        ?: return@put call.respond(
                            HttpStatusCode.BadRequest
                        )

                val ip =
                    call.extractFromParam(
                        `SettingConst.kt`.IP_PARAM
                    )

                call.respond(
                    productImageService.updatePreview(
                        employeeId = employeeId,
                        roleId = roleId,
                        imageId = imageId,
                        ip = ip,
                        baseUrl = baseUrl
                    )
                )
            }

            delete(`SettingConst.kt`.DELETE_SINGLE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                val imageId =
                    call.parameters[`SettingConst.kt`.IMAGE_ID]
                        ?: return@delete call.respond(
                            HttpStatusCode.BadRequest
                        )

                val ip =
                    call.extractFromParam(
                        `SettingConst.kt`.IP_PARAM
                    )

                call.respond(
                    productImageService.deleteSingle(
                        employeeId = employeeId,
                        roleId = roleId,
                        imageId = imageId,
                        ip = ip
                    )
                )
            }

            delete(`SettingConst.kt`.DELETE_ALL_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                val productId =
                    call.parameters[`SettingConst.kt`.PRODUCT_ID]
                        ?: return@delete call.respond(
                            HttpStatusCode.BadRequest
                        )

                val ip =
                    call.extractFromParam(
                        `SettingConst.kt`.IP_PARAM
                    )

                call.respond(
                    productImageService.deleteAll(
                        employeeId = employeeId,
                        roleId = roleId,
                        productId = productId,
                        ip = ip
                    )
                )
            }
        }
    }
}