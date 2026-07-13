package com.shayan.feature.product_image.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.product_image.constants.ProductImageConst
import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR

import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.client.request.request
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.auth.authenticate
import io.ktor.server.http.content.staticFiles
import io.ktor.server.plugins.origin
import io.ktor.server.request.host
import io.ktor.server.request.port
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import java.io.File

fun Route.productImageRoute(
    productImageService: ProductImageService
) {



    staticFiles(
        remotePath = ProductImageConst.IMAGE_ROUTE,
        dir = File(ProductImageConst.FILE_PATH)
    )

    route(ProductImageConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(ProductImageConst.ADD_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var productId: String? = null
                var ip: String? = null
                var previewImage: Boolean? = null
                var fileName: String? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            fileBytes =
                                part.streamProvider().readBytes()

                            fileName =
                                part.originalFileName
                        }

                        is PartData.FormItem -> {
                            when (part.name) {
                                ProductImageConst.PRODUCT_ID -> {
                                    productId = part.value
                                }

                                ProductImageConst.PREVIEW_PARAM -> {
                                    previewImage = part.value.toBoolean()
                                }

                                ProductImageConst.IP_PARAM -> {
                                    ip = part.value
                                }
                            }
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(
                        ProductImageConst.MISSING_FILE,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (productId == null) {
                    return@post call.respondText(
                        ProductImageConst.MISSING_PRODUCT_ID,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (ip == null) {
                    return@post call.respondText(
                        ProductImageConst.MISSING_IP,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (previewImage == null) {
                    return@post call.respondText(
                        ProductImageConst.MISSING_PREVIEW,
                        status = HttpStatusCode.BadRequest
                    )
                }

                val result =
                    productImageService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        productId = productId,
                        ip = ip,
                        fileBytes = fileBytes,
                        originalFileName = fileName,
                        previewImage = previewImage,
                        baseUrl = baseUrl
                    )

                call.respond(result)
            }

            get(ProductImageConst.READ_PREVIEW_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val productId = call.extractFromParam(ProductImageConst.PRODUCT_ID)

                call.respond(
                    productImageService.readPreview(
                        productId = productId,
                        baseUrl = baseUrl
                    )
                )
            }

            get(ProductImageConst.READ_ALL_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val productId = call.extractFromParam(ProductImageConst.PRODUCT_ID)

                call.respond(
                    productImageService.readAll(
                        productId = productId,
                        baseUrl = baseUrl
                    )
                )
            }

            put(ProductImageConst.UPDATE_IMAGE_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                var imageId: String? = null
                var ip: String? = null

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            fileBytes =
                                part.streamProvider().readBytes()
                        }

                        is PartData.FormItem -> {
                            when (part.name) {
                                ProductImageConst.IMAGE_ID -> {
                                    imageId = part.value
                                }

                                ProductImageConst.IP_PARAM -> {
                                    ip = part.value
                                }
                            }
                        }

                        else -> Unit
                    }


                    part.dispose()
                }

                if (fileBytes == null) {
                    return@put call.respondText(
                        ProductImageConst.MISSING_FILE,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (imageId == null) {
                    return@put call.respondText(
                        ProductImageConst.MISSING_IMAGE_ID,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (ip == null) {
                    return@put call.respondText(
                        ProductImageConst.MISSING_PREVIEW,
                        status = HttpStatusCode.BadRequest
                    )
                }
                val result =
                    productImageService.updateImage(
                        employeeId = employeeId,
                        roleId = roleId,
                        imageId = imageId,
                        ip = ip,
                        fileBytes = fileBytes,
                        baseUrl = baseUrl
                    )

                call.respond(result)
            }

            put(ProductImageConst.UPDATE_PREVIEW_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request = call.receive<IdIpDTO>()

                call.respond(
                    productImageService.updatePreview(
                        employeeId = employeeId,
                        roleId = roleId,
                        request =request,
                        baseUrl = baseUrl
                    )
                )
            }

            delete(ProductImageConst.DELETE_SINGLE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request = call.receive<IdIpDTO>()

                call.respond(
                    productImageService.deleteSingle(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            delete(ProductImageConst.DELETE_ALL_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request = call.receive<IdIpDTO>()

                call.respond(
                    productImageService.deleteAll(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }
        }
    }
}