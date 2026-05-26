package com.shayan.feature.audit_logs.route

import com.shayan.feature.audit_logs.constants.AuditLogConst
import com.shayan.feature.audit_logs.service.AuditLogService
import core.util.checkIfIsEmployee
import core.util.extractFromParam
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.auditLogRoutes(
    auditLogService: AuditLogService
) {
    route(AuditLogConst.MAIN_ROUTE) {

        authenticate {
            post(AuditLogConst.GET_ROUTE) {
                call.checkIfIsEmployee()
                val userId = call.extractFromParam(AuditLogConst.GET_PARAM)
                call.respond(auditLogService.get(userId))
            }
        }
    }
}