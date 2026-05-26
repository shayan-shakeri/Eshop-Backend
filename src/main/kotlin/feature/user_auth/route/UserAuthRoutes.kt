package com.shayan.feature.user_auth.route

import com.shayan.feature.auth.constants.UserAuthConst
import com.shayan.feature.user_auth.service.UserAuthService
import core.util.extractFromParam
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userAuthRoutes(
    userAuthService: UserAuthService
) {
    route(UserAuthConst.MAIN_ROUTE) {
        post(UserAuthConst.GET_ROUTE) {
            val token = call.extractFromParam(UserAuthConst.GET_PARAM)
            call.respond(userAuthService.generateAccessToken(token))
        }
    }
}