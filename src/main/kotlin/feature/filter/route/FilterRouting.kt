package com.shayan.feature.filter.route

import com.shayan.feature.category.constants.CategoryConst
import com.shayan.feature.filter.constants.FilterConst
import com.shayan.feature.filter.service.FilterService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.auth.authenticate
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

fun Route.filterRouting(
    filterService: FilterService
) {
    route(FilterConst.MAIN_ROUTE) {

        get(FilterConst.READ_ROUTE) {
            val baseUrl =
                "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"
            call.respond(filterService.read(baseUrl))
        }

        authenticate(CJWT.ACCESS_AUTH) {

            post(FilterConst.ADD_ROUTE) {
                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE) {
                    return@post
                }

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var filterName: String? = null
                var categoryId: String? = null
                var fileName: String? = null
                var ip: String? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {
                            if (part.name == FilterConst.IMAGE) {
                                fileBytes = part.streamProvider().readBytes()
                                fileName = part.originalFileName
                            }
                        }

                        is PartData.FormItem -> {
                            when (part.name) {

                                FilterConst.FILTER_NAME -> {
                                    filterName = part.value
                                }

                                FilterConst.CATEGORY_ID -> {
                                    categoryId = part.value
                                }

                                FilterConst.IP -> {
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
                        FilterConst.MISSING_FILE_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (filterName.isNullOrBlank()) {
                    return@post call.respondText(
                        FilterConst.FILTER_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (categoryId.isNullOrBlank()) {
                    return@post call.respondText(
                        FilterConst.CATEGORY_ID_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (ip.isNullOrBlank()) {
                    return@post call.respondText(
                        FilterConst.IP_NULL_ERROR,
                        status = HttpStatusCode.BadRequest
                    )
                }

                val result = filterService.add(
                    employeeId = employeeId,
                    roleId = roleId,
                    ip = ip,
                    filterName = filterName,
                    categoryId = categoryId,
                    fileBytes = fileBytes,
                    baseUrl = baseUrl,
                    originalFilename = fileName
                )

                call.respond(result)
            }

        }
    }
}