package com.shayan.feature.audit_logs.route

import com.shayan.feature.audit_logs.constants.AuditLogConst
import com.shayan.feature.audit_logs.service.AuditLogService
import core.util.extractFromParam
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.auditLogRoutes(
    auditLogService: AuditLogService
) {
    route(AuditLogConst.MAIN_ROUTE) {
//commented auth for testing
//        authenticate(CJWT.ACCESS_AUTH) {
        get(AuditLogConst.GET_ROUTE) {
            //               call.checkIfIsEmployee()
            val userId = call.extractFromParam(AuditLogConst.GET_PARAM)
            call.respond(auditLogService.get(userId))
//            }
        }
    }
}