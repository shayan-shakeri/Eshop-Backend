package com.shayan.feature.filter.route

import com.shayan.feature.filter.constants.FilterConst
import com.shayan.feature.filter.service.FilterService
import com.shayan.feature.product_image.constants.ProductImageConst
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

fun Route.filterRoute(
    filterService: FilterService
) {



    staticFiles(
        remotePath = FilterConst.REMOTE_PATH,
        dir = File(FilterConst.FILE_PATH)
    )

    route(FilterConst.MAIN_ROUTE) {



        authenticate(CJWT.ACCESS_AUTH){
            post(FilterConst.ADD_ROUTE) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var originalFileName: String? = null

                var filterName: String? = null
                var categoryId: String? = null
                var ip: String? = null

                val employeeId = call.idExtractor()
                val roleCode = call.roleCodeExtract()

                if (roleCode.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FormItem -> {
                            when (part.name) {

                                FilterConst.FILTER_NAME ->
                                    filterName = part.value

                                FilterConst.CATEGORY_ID_ENTRY ->
                                    categoryId = part.value

                                FilterConst.IP ->
                                    ip = part.value
                            }
                        }

                        is PartData.FileItem -> {
                            if (part.name == FilterConst.IMAGE) {

                                fileBytes =
                                    part.streamProvider().readBytes()

                                originalFileName =
                                    part.originalFileName
                            }
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(
                        FilterConst.MISSING_FILE_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (filterName == null) {
                    return@post call.respondText(
                        FilterConst.FILTER_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (categoryId == null) {
                    return@post call.respondText(
                        FilterConst.CATEGORY_ID_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (ip == null) {
                    return@post call.respondText(
                        FilterConst.IP_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                call.respond(
                    filterService.add(
                        filterName = filterName!!,
                        categoryId = categoryId!!,
                        ip = ip!!,
                        fileBytes = fileBytes!!,
                        originalFilename = originalFileName,
                        baseUrl = baseUrl,
                        employeeId = employeeId,
                        roleId = roleCode
                    )
                )
            }
        }

        get(FilterConst.READ_ROUTE) {

            call.respond(
                filterService.read(baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"
                )
            )
        }
    }
}