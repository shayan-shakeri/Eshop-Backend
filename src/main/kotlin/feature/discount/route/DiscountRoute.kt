package com.shayan.feature.discount.route

import com.shayan.feature.discount.constants.DiscountConst
import com.shayan.feature.discount.dto.AddDiscountRequest
import com.shayan.feature.discount.dto.UpdateDiscountRequest
import com.shayan.feature.discount.service.DiscountService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.discountRoute(
    discountService: DiscountService
) {

    route(DiscountConst.MAIN_ROUTE) {

        /*
         * Public discount calculation
         */
        get("/calculate/{productId}/{quantity}") {

            val productId =
                call.parameters[DiscountConst.PRODUCT_ID_PARAM]
                    ?: throw IllegalArgumentException()

            val quantity =
                call.parameters["quantity"]
                    ?.toInt()
                    ?: throw IllegalArgumentException()

            val userId =
                call.request.queryParameters[
                    DiscountConst.USER_ID_PARAM
                ]

            call.respond(
                discountService.calculateApplicableDiscount(
                    productId = productId,
                    userId = userId,
                    quantity = quantity
                )
            )
        }

        authenticate(CJWT.ACCESS_AUTH) {

            post(DiscountConst.ADD_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val ip =
                    call.extractFromParam(
                        DiscountConst.IP_PARAM
                    )

                val request =
                    call.receive<AddDiscountRequest>()

                call.respond(
                    discountService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        ip = ip,
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

                val id =
                    call.extractFromParam(
                        DiscountConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        DiscountConst.IP_PARAM
                    )

                val request =
                    call.receive<UpdateDiscountRequest>()

                call.respond(
                    discountService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip,
                        request = request
                    )
                )
            }

            put(DiscountConst.ACTIVATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val id =
                    call.extractFromParam(
                        DiscountConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        DiscountConst.IP_PARAM
                    )

                call.respond(
                    discountService.activate(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip
                    )
                )
            }

            put(DiscountConst.DEACTIVATE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()
                val id =
                    call.extractFromParam(
                        DiscountConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        DiscountConst.IP_PARAM
                    )

                call.respond(
                    discountService.deactivate(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip
                    )
                )
            }

            delete(DiscountConst.DELETE_ROUTE) {

                val employeeId =
                    call.idExtractor()

                val roleId =
                    call.roleCodeExtract()

                val id =
                    call.extractFromParam(
                        DiscountConst.ID_PARAM
                    )

                val ip =
                    call.extractFromParam(
                        DiscountConst.IP_PARAM
                    )

                call.respond(
                    discountService.delete(
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