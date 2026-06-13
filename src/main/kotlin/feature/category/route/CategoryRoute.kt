package com.shayan.feature.category.route

import com.shayan.feature.category.constants.CategoryConst
import com.shayan.feature.category.repository.CategoryRepository
import com.shayan.feature.category.service.CategoryService
import com.shayan.feature.user_pic.constants.UserPicConst
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleIdExtract
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
            call.respond(categoryService.readCategory(baseUrl))
        }

        authenticate(CJWT.ACCESS_AUTH) {

            post(CategoryConst.ADD_ROUTE) {
                call.checkIfIsEmployee()

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId = call.idExtractor()
                val roleId = call.roleIdExtract()

                if (roleId.toInt() == 2) {

                    val multipart = call.receiveMultipart()

                    var fileBytes: ByteArray? = null
                    var fileName: String? = null
                    var categoryName: String? = null

                    multipart.forEachPart { part ->
                        when (part) {

                            is PartData.FileItem -> {
                                fileBytes = part.streamProvider().readBytes()
                                fileName = part.originalFileName
                            }

                            is PartData.FormItem -> {
                                when (part.name) {
                                    "categoryName" -> categoryName = part.value
                                }
                            }

                            else -> Unit
                        }

                        part.dispose()
                    }

                    if (fileBytes == null) {
                        return@post call.respondText(
                            UserPicConst.MISSING_FILE,
                            status = HttpStatusCode.BadRequest
                        )
                    }

                    if (categoryName.isNullOrBlank()) {
                        return@post call.respondText(
                            "Category name is required",
                            status = HttpStatusCode.BadRequest
                        )
                    }

                    val ip = call.request.origin.remoteHost

                    val result = categoryService.addCategory(
                        employeeId = employeeId,
                        roleId = roleId,
                        ip = ip,
                        categoryName = categoryName!!,
                        fileBytes = fileBytes!!,
                        baseUrl = baseUrl
                    )

                    call.respond(result)
                }
            }
        }
    }
}