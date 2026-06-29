package com.shayan.feature.error_log.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.error_log.constants.ErrorLogConst
import com.shayan.feature.error_log.service.ErrorLogService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.errorLogRoute(
    errorLogService: ErrorLogService
) {

    route(
        ErrorLogConst.MAIN_ROUTE
    ) {

        authenticate(
            CJWT.ACCESS_AUTH
        ) {

            get(
                ErrorLogConst.READ_ROUTE
            ) {

                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.DEVELOPER){
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    errorLogService.read()
                )
            }

            delete(
                ErrorLogConst.DELETE_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val request =
                    call.receive<IdIpDTO>()

                if (roleId.toInt() != ACR.DEVELOPER){
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }



                call.respond(
                    errorLogService.delete(
                        employeeId,
                        roleId,
                        request
                    )
                )
            }

            delete(
                ErrorLogConst.DELETE_ALL_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.DEVELOPER){
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }

                val request =
                    call.receive<String>()


                call.respond(

                    errorLogService.deleteAll(
                        employeeId,
                        roleId,
                        request
                    )
                )
            }
        }
    }
}