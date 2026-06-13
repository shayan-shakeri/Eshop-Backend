package com.shayan.feature.employee.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.employee.dto.LoginEmployee
import com.shayan.feature.employee.dto.UpdateEmployeeInfo
import com.shayan.feature.employee.dto.UpdateEmployeePassword
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleIdExtract
import core.consts.CJWT
import core.util.extractFromParam
import feature.employee.constants.EmployeeConst
import feature.employee.dto.SignupEmployee
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.employeeRoute(
    employeeService: EmployeeService
) {
    route(EmployeeConst.MAIN_ROUTE) {
        post(EmployeeConst.LOGIN_ROUTE) {
            val request = call.receive<LoginEmployee>()
            call.respond(employeeService.login(request))
        }
        authenticate(CJWT.ACCESS_AUTH) {
            post(EmployeeConst.CREATE_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 0 || roleId.toInt() == 4) {
                    val request = call.receive<SignupEmployee>()
                    val employeeId = call.idExtractor()
                    call.respond(employeeService.signup(request, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }
            }

            put(EmployeeConst.UPDATE_INFO_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 0 || roleId.toInt() == 4) {
                    val request = call.receive<UpdateEmployeeInfo>()
                    val employeeId = call.idExtractor()
                    call.respond(employeeService.updateInfo(request, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }
            }

            put(EmployeeConst.UPDATE_PASSWORD_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 0 || roleId.toInt() == 4) {
                    val request = call.receive<UpdateEmployeePassword>()
                    val employeeId = call.idExtractor()
                    call.respond(employeeService.updatePassword(request, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }
            }

            delete(EmployeeConst.DELETE_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 0 || roleId.toInt() == 4) {
                    val request = call.receive<IdIpDTO>()
                    val employeeId = call.idExtractor()
                    call.respond(employeeService.deleteEmployee(employeeId, roleId, request))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }
            }

            get(EmployeeConst.GET_ALL_ROUTE) {
                call.checkIfIsEmployee()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 0 || roleId.toInt() == 4) {
                    val employeeId = call.idExtractor()
                    val ip = call.extractFromParam(EmployeeConst.IP_PARAM)
                    call.respond(employeeService.getAll(ip, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }
            }
        }
    }

}