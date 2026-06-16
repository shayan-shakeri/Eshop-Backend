package com.shayan.feature.employee_audit_log.route

import com.shayan.feature.employee_audit_log.constants.EmployeeAuditLogConst
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Routing.employeeAuditLog(
    employeeAuditLogService: EmployeeAuditLogService,
) {
    route(EmployeeAuditLogConst.MAIN_ROUTE) {
        authenticate(CJWT.ACCESS_AUTH) {
            get(EmployeeAuditLogConst.READ_ROUTE) {
                call.checkIfIsEmployee()
                val isAuthorized = call.roleCodeExtract().toInt()
                if (isAuthorized == ACR.DEVELOPER) {
                    val employeeId = call.extractFromParam(
                        EmployeeAuditLogConst.EMPLOYEE_ID_PARAM
                    )
                    call.respond(
                        employeeAuditLogService.readAuditLog(employeeId)
                    )
                }else{
                    call.respond(HttpStatusCode.Unauthorized)
                    return@get
                }
            }
        }
    }
}