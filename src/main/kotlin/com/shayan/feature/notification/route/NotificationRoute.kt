package com.shayan.feature.notification.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.notification.constants.NotificationConst
import com.shayan.feature.notification.dto.UpdateNotificationRequest
import com.shayan.feature.notification.service.NotificationService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.notificationRoute(
    notificationService: NotificationService
) {

    route(NotificationConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            get(NotificationConst.READ_ROUTE) {

                val userId =
                    call.idExtractor()

                call.respond(
                    notificationService.readNotifications(
                        userId
                    )
                )
            }

            put(NotificationConst.UPDATE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<UpdateNotificationRequest>()

                call.respond(
                    notificationService.openNotification(
                        userId,
                        request
                    )
                )
            }

            delete(NotificationConst.DELETE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    notificationService.deleteNotification(
                        userId,
                        request
                    )
                )
            }
        }
    }
}