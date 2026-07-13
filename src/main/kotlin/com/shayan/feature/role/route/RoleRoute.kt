package com.shayan.feature.role.route

import com.shayan.feature.role.constants.RoleConst
import com.shayan.feature.role.dto.AddRole
import com.shayan.feature.role.model.Role
import com.shayan.feature.role.service.RoleService
import com.shayan.util.jwt.checkIfIsEmployee
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
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
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() == ACR.CEO) {
                    val request = call.receive<AddRole>()
                    call.respond(roleService.add(request, employeeId, roleId))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

            }
            get(RoleConst.READ_All_ROUTE){
                call.checkIfIsEmployee()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() == ACR.HR || roleId.toInt() == ACR.CEO) {
                    call.respond(roleService.getAll())
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }
            }
        }
    }
}