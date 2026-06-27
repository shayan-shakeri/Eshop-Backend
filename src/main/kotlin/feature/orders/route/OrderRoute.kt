package feature.orders.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.order.constants.OrderConst
import com.shayan.feature.order.dto.AddOrderRequest
import com.shayan.feature.order.service.OrderService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import feature.orders.dto.UpdateOrderRequest
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.orderRoute(
    orderService: OrderService
) {

    route(OrderConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(OrderConst.ADD_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddOrderRequest>()

                call.respond(
                    orderService.addOrder(
                        userId = userId,
                        request = request
                    )
                )
            }

            get(OrderConst.READ_ROUTE) {

                val userId =
                    call.idExtractor()

                call.respond(
                    orderService.readUserOrders(
                        userId
                    )
                )
            }

            get(OrderConst.READ_SINGLE_ROUTE) {

                val userId =
                    call.idExtractor()

                val orderId =
                    call.extractFromParam(
                        OrderConst.ID_PARAM
                    )

                call.respond(
                    orderService.readSingle(
                        userId,
                        orderId
                    )
                )
            }


            put(OrderConst.UPDATE_ROUTE) {

                val request =
                    call.receive<UpdateOrderRequest>()

                call.respond(
                    orderService.updateDelivery(
                        request
                    )
                )
            }

            delete(OrderConst.DELETE_ROUTE) {

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    orderService.deleteOrder(
                        request
                    )
                )
            }
        }
    }
}