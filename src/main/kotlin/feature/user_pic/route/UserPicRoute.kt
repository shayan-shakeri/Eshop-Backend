package com.shayan.feature.user_pic.route

import com.shayan.feature.user_pic.constants.UserPicConst
import com.shayan.feature.user_pic.repository.UserPicRepository
import com.shayan.feature.user_pic.service.UserPicService
import com.shayan.util.callUtil.idExtractor
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
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import java.io.File

fun Route.userPicRoute(
    userPicService: UserPicService
) {
    route(UserPicConst.MAIN_ROUTE) {

        staticFiles(
            remotePath = UserPicConst.REMOTE_PATH,
            dir = File(UserPicConst.FILE_PATH)
        )

        authenticate(CJWT.ACCESS_AUTH) {

            post(UserPicConst.ADD_ROUTE) {
                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"
                val ip = call.extractFromParam(UserPicConst.IP_PARAM)
                val userId = call.idExtractor()

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var fileName: String? = null

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            fileBytes = part.streamProvider().readBytes()
                            fileName = part.originalFileName
                        }

                        else -> Unit
                    }
                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(UserPicConst.MISSING_FILE, status = HttpStatusCode.BadRequest)
                }

                val result = userPicService.addUserPic(
                    userId = userId,
                    ip = ip,
                    fileBytes = fileBytes,
                    originalFileName = fileName,
                    baseUrl = baseUrl
                )

                call.respond(result)
            }

            get(UserPicConst.READ_ROUTE) {
                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"
                val userId =  call.idExtractor()
                val result = userPicService.readUserPic(userId, baseUrl)

                call.respond(result)
            }

            put(UserPicConst.UPDATE_ROUTE) {
                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"
                val ip = call.extractFromParam(UserPicConst.IP_PARAM)
                val userId = call.idExtractor()

                val multipart = call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var fileName: String? = null

                multipart.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            fileBytes = part.streamProvider().readBytes()
                            fileName = part.originalFileName
                        }
                        else -> Unit
                    }
                    part.dispose()
                }

                if (fileBytes == null) {
                    return@put call.respondText(UserPicConst.MISSING_FILE, status = HttpStatusCode.BadRequest)
                }

                val result = userPicService.updateUserPic(
                    userId = userId,
                    ip = ip,
                    fileBytes = fileBytes,
                    originalFileName = fileName,
                    baseUrl = baseUrl
                )

                call.respond(result)
            }

            delete(UserPicConst.DELETE_ROUTE) {
                val ip = call.extractFromParam(UserPicConst.IP_PARAM)
                val userId = call.idExtractor()
                call.respond(userPicService.deleteUserPic(userId, ip))
            }
        }
    }
}