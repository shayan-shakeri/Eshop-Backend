package com.shayan.feature.banner.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.banner.constants.BannerConst
import com.shayan.feature.banner.dto.UpdateBannerRequest
import com.shayan.feature.banner.service.BannerService
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
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import java.io.File

fun Route.bannerRoute(
    bannerService: BannerService
) {

    staticFiles(
        remotePath = BannerConst.IMAGE_ROUTE,
        dir = File(
            BannerConst.FILE_PATH
        )
    )

    route(
        BannerConst.MAIN_ROUTE
    ) {

        get(
            BannerConst.READ_ACTIVE_ROUTE
        ) {

            call.respond(
                bannerService.readActive()
            )
        }

        authenticate(
            CJWT.ACCESS_AUTH
        ) {

            post(
                BannerConst.ADD_ROUTE
            ) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.MARKETING) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                val multipart =
                    call.receiveMultipart()

                var fileBytes:
                        ByteArray? = null

                var active:
                        Boolean? = null

                var ip:
                        String? = null

                var fileName:
                        String? = null

                multipart.forEachPart { part ->

                    when (part) {

                        is PartData.FileItem -> {

                            fileBytes =
                                part.streamProvider()
                                    .readBytes()

                            fileName =
                                part.originalFileName
                        }

                        is PartData.FormItem -> {

                            when (part.name) {

                                BannerConst.ACTIVE_PARAM -> {
                                    active =
                                        part.value.toBoolean()
                                }

                                BannerConst.IP -> {
                                    ip =
                                        part.value
                                }
                            }
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(
                        BannerConst.MISSING_FILE,
                        status =
                            HttpStatusCode.BadRequest
                    )
                }

                if (active == null) {
                    return@post call.respondText(
                        BannerConst.MISSING_ACTIVE,
                        status =
                            HttpStatusCode.BadRequest
                    )
                }

                if (ip == null) {
                    return@post call.respondText(
                        BannerConst.MISSING_IP,
                        status =
                            HttpStatusCode.BadRequest
                    )
                }

                call.respond(
                    bannerService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        fileBytes = fileBytes,
                        originalFileName = fileName,
                        active = active,
                        ip = ip,
                        baseUrl = baseUrl
                    )
                )
            }

            get(
                BannerConst.READ_ROUTE
            ) {

                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.MARKETING) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    bannerService.read()
                )
            }

            put(
                BannerConst.UPDATE_ROUTE
            ) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.MARKETING) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }

                val request =
                    call.receive<
                            UpdateBannerRequest
                            >()

                call.respond(
                    bannerService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request,
                        baseUrl = baseUrl
                    )
                )
            }

            delete(
                BannerConst.DELETE_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.MARKETING) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    bannerService.delete(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }
        }
    }
}