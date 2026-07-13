package com.shayan.feature.monthly_log.route

import com.shayan.feature.monthly_log.constants.MonthlyLogConst
import com.shayan.feature.monthly_log.service.MonthlyLogService
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.monthlyLogRoute(
    service: MonthlyLogService
) {

    route(
        MonthlyLogConst.MAIN_ROUTE
    ) {

        authenticate(CJWT.ACCESS_AUTH){
            get(
                MonthlyLogConst.READ_ROUTE
            ) {

                val roleId = call.roleCodeExtract().toInt()
                if (roleId != ACR.CEO) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    service.readAll()
                )
            }

            get(
                MonthlyLogConst.READ_SINGLE_ROUTE
            ) {

                val roleId = call.roleCodeExtract().toInt()
                if (roleId != ACR.CEO) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                val id =
                    call.extractFromParam(MonthlyLogConst.ID)


                call.respond(
                    service.readById(
                        id
                    )
                )
            }

            get(
                MonthlyLogConst.READ_CURRENT_ROUTE
            ) {

                val roleId = call.roleCodeExtract().toInt()
                if (roleId != ACR.CEO) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    service.readCurrent()
                )
            }
        }

    }
}