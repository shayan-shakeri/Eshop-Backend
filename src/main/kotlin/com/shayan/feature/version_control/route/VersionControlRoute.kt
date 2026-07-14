package com.shayan.feature.version_control.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.version_control.constants.VersionControlConst
import com.shayan.feature.version_control.dto.AddVersionRequest
import com.shayan.feature.version_control.dto.UpdateVersionControlRequest
import com.shayan.feature.version_control.dto.VerifyVersionRequest
import com.shayan.feature.version_control.service.VersionControlService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.versionControlRoute(
    versionControlService: VersionControlService
) {

    route(
        VersionControlConst.MAIN_ROUTE
    ) {

        post(VersionControlConst.VERIFY_ROUTE) {

            val request = call.receive<VerifyVersionRequest>()

            call.respond(versionControlService.verify(request))
        }

        authenticate(
            CJWT.ACCESS_AUTH
        ) {


            get(
                VersionControlConst.READ_ROUTE
            ) {
                val roleId =
                    call.roleCodeExtract()
                if (roleId.toInt() != ACR.DEVELOPER) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@get
                }

                call.respond(
                    versionControlService.read()
                )
            }

            post(
                VersionControlConst.ADD_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.DEVELOPER) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                val request =
                    call.receive<
                            AddVersionRequest
                            >()

                call.respond(
                    versionControlService.add(
                        employeeId,
                        roleId,
                        request
                    )
                )
            }

            put(
                VersionControlConst.UPDATE_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.DEVELOPER) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }

                val request =
                    call.receive<
                            UpdateVersionControlRequest
                            >()

                call.respond(
                    versionControlService.update(
                        employeeId,
                        roleId,
                        request
                    )
                )
            }

            delete(
                VersionControlConst.DELETE_ROUTE
            ) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.DEVELOPER) {
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }

                val request =
                    call.receive<IdIpDTO>()

                versionControlService.delete(
                    employeeId,
                    roleId,
                    request
                )

                call.respond(
                    "Deleted Successfully"
                )
            }
        }
    }
}