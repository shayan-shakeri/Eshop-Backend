package com.shayan.feature.role.route

import com.shayan.feature.role.constants.RoleConst
import com.shayan.feature.role.dto.AddRole
import com.shayan.feature.role.service.RoleService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleIdExtract
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.roleRoute(
    roleService: RoleService,
) {
    route(RoleConst.MAIN_ROUTE) {
        authenticate(CJWT.ACCESS_AUTH) {
            get(RoleConst.READ_ROUTE) {
                call.checkIfIsEmployee()
                val id = call.extractFromParam(RoleConst.ROLE_PARAM)
                call.respond(roleService.read(id))
            }
            post(RoleConst.ADD_ROUTE) {
                call.checkIfIsEmployee()
                val employeeId = call.idExtractor()
                val roleId = call.roleIdExtract()
                if (roleId.toInt() == 4) {
                    val request = call.receive<AddRole>()
                    call.respond(roleService.add(request, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Unauthorized)
                    return@post
                }
            }
        }
    }
}