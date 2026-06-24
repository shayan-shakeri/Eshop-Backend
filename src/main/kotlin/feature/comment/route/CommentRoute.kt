package com.shayan.feature.comment.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.comment.constants.CommentConst
import com.shayan.feature.comment.dto.AddCommentRequest
import com.shayan.feature.comment.dto.UpdateCommentRequest
import com.shayan.feature.comment.service.CommentService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.commentRoute(
    commentService: CommentService
) {

    route(CommentConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(CommentConst.ADD_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddCommentRequest>()

                call.respond(
                    commentService.addComment(
                        userId = userId,
                        request = request
                    )
                )
            }

            put(CommentConst.UPDATE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<UpdateCommentRequest>()

                call.respond(
                    commentService.updateComment(
                        userId = userId,
                        request = request
                    )
                )
            }

            delete(CommentConst.DELETE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    commentService.deleteComment(
                        userId = userId,
                        request = request
                    )
                )
            }
        }

        get(CommentConst.READ_ROUTE) {

            val productId =
                call.extractFromParam(
                    CommentConst.PRODUCT_ID_PARAM
                )

            call.respond(
                commentService.readComments(
                    productId = productId
                )
            )
        }
    }
}