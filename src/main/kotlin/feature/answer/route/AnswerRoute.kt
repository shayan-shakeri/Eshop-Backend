package com.shayan.feature.answer.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.answer.constants.AnswerConst
import com.shayan.feature.answer.dto.AddAnswerRequest
import com.shayan.feature.answer.dto.UpdateAnswerRequest
import com.shayan.feature.answer.service.AnswerService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.answerRoute(
    answerService: AnswerService
) {

    route(AnswerConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(AnswerConst.ADD_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddAnswerRequest>()

                call.respond(
                    answerService.addAnswer(
                        userId = userId,
                        request = request
                    )
                )
            }

            put(AnswerConst.UPDATE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<UpdateAnswerRequest>()

                call.respond(
                    answerService.updateAnswer(
                        userId = userId,
                        request = request
                    )
                )
            }

            delete(AnswerConst.DELETE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    answerService.deleteAnswer(
                        userId = userId,
                        request = request
                    )
                )
            }
        }

        get(AnswerConst.READ_ROUTE) {

            val questionCommentId =
                call.extractFromParam(
                    AnswerConst.QUESTION_COMMENT_ID_PARAM
                )

            call.respond(
                answerService.readAnswers(
                    questionCommentId
                )
            )
        }
    }
}