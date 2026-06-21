package com.shayan.feature.setting.route

import com.shayan.feature.setting.constants.SettingConst
import com.shayan.feature.setting.dto.AddSettingRequest
import com.shayan.feature.setting.dto.UpdateSettingRequest
import com.shayan.feature.setting.service.SettingService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract

import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.settingRoute(
    settingService: SettingService
) {

    route(SettingConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(SettingConst.ADD_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val ip =
                    call.extractFromParam(
                        SettingConst.IP_PARAM
                    )

                val request =
                    call.receive<AddSettingRequest>()

                call.respond(
                    settingService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        ip = ip,
                        request = request
                    )
                )
            }

            get(SettingConst.READ_ROUTE) {

                val id =
                    call.extractFromParam(
                        SettingConst.ID_PARAM
                    )

                call.respond(
                    settingService.read(id)
                )
            }

            get(SettingConst.READ_ALL_ROUTE) {

                call.respond(
                    settingService.readAll()
                )
            }

            get(SettingConst.READ_PRODUCT_ROUTE) {

                val productId =
                    call.extractFromParam(
                        SettingConst.PRODUCT_ID_PARAM
                    )

                call.respond(
                    settingService.readByProduct(
                        productId
                    )
                )
            }

            put(SettingConst.UPDATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val id =
                    call.extractFromParam(
                        SettingConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        SettingConst.IP_PARAM
                    )

                val request =
                    call.receive<UpdateSettingRequest>()

                call.respond(
                    settingService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip,
                        request = request
                    )
                )
            }

            delete(SettingConst.DELETE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val id =
                    call.extractFromParam(
                        SettingConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        SettingConst.IP_PARAM
                    )

                call.respond(
                    settingService.delete(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip
                    )
                )
            }
        }
    }
}