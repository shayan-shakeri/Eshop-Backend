package com.shayan.feature.support_message.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.support_message.constants.SupportMessageConst
import com.shayan.feature.support_message.dto.AddSupportTextMessageRequest
import com.shayan.feature.support_message.service.SupportMessageService
import com.shayan.feature.support_message.websocket.SupportSessionManager
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.CJWT
import core.consts.EXC
import core.exception.AuthenticationException
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.http.content.staticFiles
import io.ktor.server.plugins.origin
import io.ktor.server.request.host
import io.ktor.server.request.port
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import java.io.File

fun Route.supportMessageRoute(
    supportMessageService: SupportMessageService
) {

    staticFiles(
        remotePath = SupportMessageConst.REMOTE_PATH,
        dir = File(
            SupportMessageConst.FILE_PATH
        )
    )

    route(
        SupportMessageConst.MAIN_ROUTE
    ) {

        authenticate(
            CJWT.ACCESS_AUTH
        ) {

            post(
                SupportMessageConst.ADD_TEXT_ROUTE
            ) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddSupportTextMessageRequest>()

                val roleCode = call.roleCodeExtract()

                call.respond(
                    supportMessageService.addText(
                        userId = userId,
                        request = request,
                        roleCode = roleCode
                    )
                )
            }

            post(
                SupportMessageConst.ADD_IMAGE_ROUTE
            ) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val userId =
                    call.idExtractor()

                val multipart =
                    call.receiveMultipart()

                var fileBytes: ByteArray? = null
                var chatId: String? = null
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
                                SupportMessageConst.CHAT_ID -> {
                                    chatId = part.value
                                }
                            }
                        }

                        else -> Unit
                    }

                    part.dispose()
                }

                if (fileBytes == null) {
                    return@post call.respondText(
                        SupportMessageConst.MISSING_FILE,
                        status = HttpStatusCode.BadRequest
                    )
                }

                if (chatId == null) {
                    return@post call.respondText(
                        SupportMessageConst.MISSING_CHAT_ID,
                        status = HttpStatusCode.BadRequest
                    )
                }

                call.respond(
                    supportMessageService.addImage(
                        userId = userId,
                        supportChatId = chatId,
                        fileBytes = fileBytes,
                        originalFileName = fileName,
                        baseUrl = baseUrl
                    )
                )
            }

            get(
                SupportMessageConst.READ_ROUTE
            ) {

                val baseUrl =
                    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

                val chatId =
                    call.extractFromParam(
                        SupportMessageConst.CHAT_ID_PARAM
                    )

                call.respond(
                    supportMessageService.read(
                        supportChatId = chatId,
                        baseUrl = baseUrl
                    )
                )
            }

            delete(
                SupportMessageConst.DELETE_ROUTE
            ) {

                val request =
                    call.receive<IdIpDTO>()

                supportMessageService.delete(
                    request
                )

                call.respond(
                    HttpStatusCode.OK
                )
            }
        }

        webSocket(
            SupportMessageConst.WS_ROUTE
        ) {

            val chatId =
                call.extractFromParam(
                    SupportMessageConst.CHAT_ID_PARAM
                )

            SupportSessionManager.connect(
                chatId,
                this
            )

            try {

                for (frame in incoming) {

                    frame as? Frame.Text
                        ?: continue
                }

            } finally {

                SupportSessionManager.disconnect(
                    chatId,
                    this
                )
            }
        }
    }
}