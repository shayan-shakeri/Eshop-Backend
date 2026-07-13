package com.shayan.feature.order_product.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.order_product.constants.OrderProductConst
import com.shayan.feature.order_product.dto.AddOrderProductRequest
import com.shayan.feature.order_product.service.OrderProductService
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.orderProductRoute(
    orderProductService: OrderProductService
) {

    route(OrderProductConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(OrderProductConst.ADD_ROUTE) {

                val request =
                    call.receive<AddOrderProductRequest>()

                call.respond(
                    orderProductService.add(
                        request
                    )
                )
            }

            get(
                OrderProductConst.READ_BY_ORDER_ROUTE
            ) {

                val orderId =
                    call.extractFromParam(
                        "orderId"
                    )

                call.respond(
                    orderProductService.readByOrder(
                        orderId
                    )
                )
            }

            get(
                OrderProductConst.READ_BY_PRODUCT_ROUTE
            ) {

                val productId =
                    call.extractFromParam(
                        "productId"
                    )

                call.respond(
                    orderProductService.readByProduct(
                        productId
                    )
                )
            }

            delete(
                OrderProductConst.DELETE_ROUTE
            ) {

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    orderProductService.delete(
                        request
                    )
                )
            }
        }
    }
}