package com.shayan.feature.audit_logs.route

import com.shayan.feature.audit_logs.constants.AuditLogConst
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.auditLogRoutes(
    auditLogService: AuditLogService
) {
    route(AuditLogConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {
            get(AuditLogConst.GET_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleCodeExtract()
                if (roleId.toInt() == ACR.DEVELOPER) {
                    val userId = call.extractFromParam(AuditLogConst.GET_PARAM)
                    call.respond(auditLogService.get(userId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }
            }
        }
    }
}