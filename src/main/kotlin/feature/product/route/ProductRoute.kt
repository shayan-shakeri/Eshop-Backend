package com.shayan.feature.product.route

import com.shayan.feature.product.constants.ProductConst
import com.shayan.feature.product.dto.AddProductRequest
import com.shayan.feature.product.dto.UpdateProductRequest
import com.shayan.feature.product.service.ProductService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.plugins.origin
import io.ktor.server.request.host
import io.ktor.server.request.port
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.productRoutes(
    productService: ProductService
) {

    route(ProductConst.MAIN_ROUTE) {

        /**
         * PUBLIC READ ENDPOINTS
         */

        get(ProductConst.READ_ALL_ROUTE) {
            call.respond(productService.readAll())
        }

        get(ProductConst.READ_ROUTE) {
            val id = call.extractFromParam(ProductConst.ID_PARAM)
            call.respond(productService.read(id))
        }

        get(ProductConst.READ_CATEGORY_ROUTE) {
            val categoryId = call.extractFromParam(ProductConst.CATEGORY_ID_PARAM)
            call.respond(productService.readByCategory(categoryId))
        }

        get(ProductConst.READ_FILTER_ROUTE) {
            val filterId = call.extractFromParam(ProductConst.FILTER_ID_PARAM)
            call.respond(productService.readByFilter(filterId))
        }

        get(ProductConst.SEARCH_ROUTE) {
            val name = call.extractFromParam(ProductConst.NAME_PARAM)
            call.respond(productService.searchByName(name))
        }

        /**
         * PREVIEW (HOMEPAGE)
         */
        get(ProductConst.READ_PREVIEW_ROUTE) {
            val baseUrl =
                "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

            call.respond(
                productService.readPreview(baseUrl)
            )
        }

        /**
         * AUTHORIZED ADMIN/EMPLOYEE ACTIONS
         */
        authenticate(CJWT.ACCESS_AUTH) {

            post(ProductConst.ADD_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                val ip = call.extractFromParam(ProductConst.IP_PARAM)
                val request = call.receive<AddProductRequest>()

                call.respond(
                    productService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        ip = ip,
                        request = request
                    )
                )
            }

            put(ProductConst.UPDATE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                val id = call.extractFromParam(ProductConst.ID_PARAM)
                val ip = call.extractFromParam(ProductConst.IP_PARAM)
                val request = call.receive<UpdateProductRequest>()

                call.respond(
                    productService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip,
                        request = request
                    )
                )
            }

            delete(ProductConst.DELETE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                val id = call.extractFromParam(ProductConst.ID_PARAM)
                val ip = call.extractFromParam(ProductConst.IP_PARAM)

                call.respond(
                    productService.delete(
                        employeeId = employeeId,
                        roleId = roleId,
                        id = id,
                        ip = ip
                    )
                )
            }

            put(ProductConst.INCREASE_STOCK_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                val id = call.extractFromParam(ProductConst.ID_PARAM)
                val ip = call.extractFromParam(ProductConst.IP_PARAM)
                val amount = call.parameters["amount"]?.toInt() ?: 1

                call.respond(
                    productService.increaseStock(
                        employeeId,
                        roleId,
                        id,
                        amount,
                        ip
                    )
                )
            }

            put(ProductConst.DECREASE_STOCK_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                val id = call.extractFromParam(ProductConst.ID_PARAM)
                val ip = call.extractFromParam(ProductConst.IP_PARAM)
                val amount = call.parameters["amount"]?.toInt() ?: 1

                call.respond(
                    productService.decreaseStock(
                        employeeId,
                        roleId,
                        id,
                        amount,
                        ip
                    )
                )
            }
        }
    }
}