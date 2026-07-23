package com.shayan.feature.user_auth.route

import com.shayan.feature.user_auth.constants.UserAuthConst
import com.shayan.feature.user_auth.dto.UserRefreshRequest
import com.shayan.feature.user_auth.service.UserAuthService
import core.util.extractFromParam
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userAuthRoutes(
    userAuthService: UserAuthService
) {
    route(UserAuthConst.MAIN_ROUTE) {
        post {
            val token = call.receive<UserRefreshRequest>()
            call.respond(userAuthService.generateAccessToken(token.refreshToken))
        }
    }
}