package com.shayan.feature.product.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.product.constants.ProductConst
import com.shayan.feature.product.dto.AddProductRequest
import com.shayan.feature.product.dto.UpdateProductRequest
import com.shayan.feature.product.dto.UpdateStock
import com.shayan.feature.product.service.ProductService
import com.shayan.util.jwt.idExtractor
import com.shayan.util.jwt.roleCodeExtract
import core.consts.ACR
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.http.HttpStatusCode
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


        get(ProductConst.READ_PREVIEW_ROUTE) {
            val baseUrl =
                "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}"

            call.respond(
                productService.readPreview(baseUrl)
            )
        }


        authenticate(CJWT.ACCESS_AUTH) {

            post(ProductConst.ADD_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }
                val request = call.receive<AddProductRequest>()

                call.respond(
                    productService.add(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            put(ProductConst.UPDATE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()

                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request = call.receive<UpdateProductRequest>()

                call.respond(
                    productService.update(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            delete(ProductConst.DELETE_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }

                val request = call.receive<IdIpDTO>()

                call.respond(
                    productService.delete(
                        employeeId = employeeId,
                        roleId = roleId,
                        request = request
                    )
                )
            }

            put(ProductConst.INCREASE_STOCK_ROUTE) {

                val employeeId = call.idExtractor()
                val roleId = call.roleCodeExtract()
                if (roleId.toInt() != ACR.STORAGE){
                    call.respond(HttpStatusCode.Forbidden)
                }
                val request = call.receive<UpdateStock>()

                call.respond(
                    productService.increaseStock(
                        employeeId,
                        roleId,
                        request
                    )
                )
            }

            put(ProductConst.DECREASE_STOCK_ROUTE) {

                val request = call.receive<UpdateStock>()

                call.respond(
                    productService.decreaseStock(
                        request
                    )
                )
            }
        }
    }
}