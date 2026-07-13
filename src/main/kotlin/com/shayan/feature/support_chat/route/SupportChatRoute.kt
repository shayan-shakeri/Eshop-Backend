package com.shayan.feature.support_chat.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.support_chat.constants.SupportChatConst
import com.shayan.feature.support_chat.dto.AddSupportChatRequest
import com.shayan.feature.support_chat.dto.UpdateSupportChatPriorityRequest
import com.shayan.feature.support_chat.dto.UpdateSupportChatStatusRequest
import com.shayan.feature.support_chat.service.SupportChatService
import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.supportChatRoute(
    supportChatService: SupportChatService
) {

    route(
        SupportChatConst.MAIN_ROUTE
    ) {

        authenticate(
            CJWT.ACCESS_AUTH
        ) {

            post(
                SupportChatConst.ADD_ROUTE
            ) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddSupportChatRequest>()

                call.respond(
                    supportChatService.add(
                        userId,
                        request
                    )
                )
            }

            get(
                SupportChatConst.READ_ROUTE
            ) {

                val userId =
                    call.idExtractor()

                call.respond(
                    supportChatService.readByUser(
                        userId
                    )
                )
            }

            get(
                SupportChatConst.READ_SINGLE_ROUTE
            ) {

                val id =
                    call.extractFromParam(
                        SupportChatConst.ID_PARAM
                    )

                call.respond(
                    supportChatService.readById(
                        id
                    )
                )
            }

            get(SupportChatConst.READ_ALL_ROUTE) {

                val roleCode = call.roleCodeExtract()

                if (roleCode.toInt() != ACR.CUSTOMER_SUPPORT){
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    supportChatService.readAll()
                )
            }


            put(
                SupportChatConst.UPDATE_PRIORITY_ROUTE
            ) {

                val request =
                    call.receive<UpdateSupportChatPriorityRequest>()

                call.respond(
                    supportChatService.updatePriority(
                        request
                    )
                )
            }

            put(
                SupportChatConst.UPDATE_STATUS_ROUTE
            ) {

                val request =
                    call.receive<UpdateSupportChatStatusRequest>()

                call.respond(
                    supportChatService.updateStatus(
                        request
                    )
                )
            }

            delete(
                SupportChatConst.DELETE_ROUTE
            ) {

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    supportChatService.delete(
                        request
                    )
                )
            }
        }
    }
}