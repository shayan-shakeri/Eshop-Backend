package com.shayan.feature.category.route

import com.shayan.feature.category.constants.CategoryConst
import com.shayan.feature.category.service.CategoryService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
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
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import java.io.File

fun Route.categoryRoute(
    categoryService: CategoryService
) {

    route(CategoryConst.MAIN_ROUTE) {

        staticFiles(
            remotePath = CategoryConst.REMOTE_PATH,
            dir = File(CategoryConst.FILE_PATH)
        )

        get(CategoryConst.GET_ROUTE) {

            val baseUrl =
                "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

            call.respond(
                categoryService.readCategory(baseUrl)
            )
        }

        authenticate(CJWT.ACCESS_AUTH) {

            post(CategoryConst.ADD_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE) {
                    return@post
                }

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var categoryName: String? = null
                var ip: String? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            if (part.name == CategoryConst.IMAGE) {
                                fileBytes = part.streamProvider().readBytes()
                            }
                        }

                        is PartData.FormItem -> {
                            when (part.name) {

                                CategoryConst.CATEGORY_NAME -> {
                                    categoryName = part.value
                                }

                                CategoryConst.IP -> {
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
                        CategoryConst.MISSING_FILE_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (categoryName.isNullOrBlank()) {
                    return@post call.respondText(
                        CategoryConst.CATEGORY_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (ip.isNullOrBlank()) {
                    return@post call.respondText(
                        CategoryConst.IP_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                val result = categoryService.addCategory(
                    employeeId = employeeId,
                    roleId = roleId,
                    ip = ip,
                    categoryName = categoryName,
                    fileBytes = fileBytes,
                    baseUrl = baseUrl
                )

                call.respond(result)
            }
        }
    }
}