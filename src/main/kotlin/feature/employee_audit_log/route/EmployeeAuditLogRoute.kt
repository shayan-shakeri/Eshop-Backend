package com.shayan.feature.employee_audit_log.route

import com.auth0.jwt.JWT
import com.shayan.feature.employee_audit_log.constants.EmployeeAuditLogConst
import com.shayan.feature.employee_audit_log.model.EmployeeAuditLog
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.util.jwt.roleIdExtract
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Routing.employeeAuditLog(
    employeeAuditLogService: EmployeeAuditLogService,
) {
    route(EmployeeAuditLogConst.MAIN_ROUTE) {
        authenticate(CJWT.ACCESS_AUTH) {
            post(EmployeeAuditLogConst.READ_ROUTE) {
                val isAuthorized = call.roleIdExtract().toInt()
                if (isAuthorized == 3 || isAuthorized == 0) {
                    val employeeId = call.extractFromParam(
                        EmployeeAuditLogConst.EMPLOYEE_ID_PARAM
                    )

                    call.respond(
                        employeeAuditLogService.readAuditLog(employeeId)
                    )
                }else{
                    call.respond(HttpStatusCode.Unauthorized)
                    return@post
                }
            }
        }
    }
}