package com.shayan.feature.discount.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.discount.constants.DiscountConst
import com.shayan.feature.discount.dto.AddDiscountRequest
import com.shayan.feature.discount.dto.UpdateDiscountRequest
import com.shayan.feature.discount.service.DiscountService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.discountRoute(
    discountService: DiscountService
) {

    route(DiscountConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(DiscountConst.ADD_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                    return@post
                }

                val request =
                    call.receive<AddDiscountRequest>()

                call.respond(
                    discountService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            get(DiscountConst.READ_ROUTE) {

                val id =
                    call.extractFromParam(
                        DiscountConst.ID_PARAM
                    )

                call.respond(
                    discountService.read(id)
                )
            }

            get(DiscountConst.READ_ALL_ROUTE) {



                call.respond(
                    discountService.readAll()
                )
            }

            get(DiscountConst.READ_PRODUCT_ROUTE) {

                val productId =
                    call.extractFromParam(
                        DiscountConst.PRODUCT_ID_PARAM
                    )

                call.respond(
                    discountService.readByProduct(
                        productId
                    )
                )
            }

            get(DiscountConst.READ_USER_ROUTE) {

                val userId =
                    call.extractFromParam(
                        DiscountConst.USER_ID_PARAM
                    )

                call.respond(
                    discountService.readByUser(
                        userId
                    )
                )
            }

            get(DiscountConst.READ_ACTIVE_ROUTE) {

                call.respond(
                    discountService.readActive()
                )
            }

            put(DiscountConst.UPDATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()



                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request =
                    call.receive<UpdateDiscountRequest>()

                call.respond(
                    discountService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            put(DiscountConst.ACTIVATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }



                val request = call.receive<IdIpDTO>()

                call.respond(
                    discountService.activate(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            put(DiscountConst.DEACTIVATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()
                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                    return@put
                }


                val request = call.receive<IdIpDTO>()
                call.respond(
                    discountService.deactivate(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            delete(DiscountConst.DELETE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()
                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                    return@delete
                }

                val request = call.receive<IdIpDTO>()

                call.respond(
                    discountService.delete(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }
        }
    }
}