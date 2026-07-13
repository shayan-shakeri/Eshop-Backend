package com.shayan.feature.users.route

import com.shayan.feature.users.constants.UsersConst
import com.shayan.feature.users.dto.UserLoginRequest
import com.shayan.feature.users.dto.UserSignupRequest
import com.shayan.feature.users.dto.UserUpdateInfoRequest
import com.shayan.feature.users.dto.UserUpdatePasswordRequest
import com.shayan.feature.users.service.UsersService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.userRoutes(
    usersService: UsersService
) {
    route(UsersConst.MAIN_ROUTE) {

        post(UsersConst.LOGIN_NORMAL_ROUTE) {
            val request = call.receive<UserLoginRequest>()
            call.respond(usersService.login(request))
        }

        post(UsersConst.SIGNUP_ROUTE) {
            val request = call.receive<UserSignupRequest>()
            call.respond(usersService.signup(request))
        }

        authenticate(CJWT.ACCESS_AUTH) {

            get(UsersConst.LOGIN_TOKEN_ROUTE) {
                val id = call.idExtractor()
                val ip = call.extractFromParam(UsersConst.IP_PARAM)
                call.respond(usersService.loginToken(id, ip))
            }

            put(UsersConst.UPDATE_INFO_ROUTE) {
                val id = call.idExtractor()
                val request = call.receive<UserUpdateInfoRequest>()
                call.respond(usersService.updateInfo(id, request))
            }

            put(UsersConst.UPDATE_PASSWORD_ROUTE) {
                val id = call.idExtractor()
                val request = call.receive<UserUpdatePasswordRequest>()
                call.respond(usersService.updatePassword(id, request))
            }

            post(UsersConst.LOGOUT_ROUTE) {
                val id = call.idExtractor()
                val ip = call.extractFromParam(UsersConst.IP_PARAM)
                call.respond(usersService.logout(id, ip))
            }

            delete(UsersConst.DELETE_ROUTE) {
                val id = call.idExtractor()
                val ip = call.extractFromParam(UsersConst.IP_PARAM)
                call.respond(usersService.deleteUser(id, ip))
            }

        }


    }
}