package com.shayan.feature.question.route

import com.shayan.core.response.IdIpDTO
import com.shayan.feature.question.constants.QuestionConst
import com.shayan.feature.question.dto.AddQuestionRequest
import com.shayan.feature.question.dto.UpdateQuestionRequest
import com.shayan.feature.question.service.QuestionService
import com.shayan.util.jwt.idExtractor
import core.consts.CJWT
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*

fun Route.questionRoute(
    questionService: QuestionService
) {

    route(QuestionConst.MAIN_ROUTE) {

        authenticate(CJWT.ACCESS_AUTH) {

            post(QuestionConst.ADD_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<AddQuestionRequest>()

                call.respond(
                    questionService.addQuestion(
                        userId = userId,
                        request = request
                    )
                )
            }

            put(QuestionConst.UPDATE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<UpdateQuestionRequest>()

                call.respond(
                    questionService.updateQuestion(
                        userId = userId,
                        request = request
                    )
                )
            }

            delete(QuestionConst.DELETE_ROUTE) {

                val userId =
                    call.idExtractor()

                val request =
                    call.receive<IdIpDTO>()

                call.respond(
                    questionService.deleteQuestion(
                        userId = userId,
                        request = request
                    )
                )
            }
        }

        get(QuestionConst.READ_ROUTE) {

            val productId =
                call.extractFromParam(
                    QuestionConst.PRODUCT_ID_PARAM
                )

            call.respond(
                questionService.readQuestions(
                    productId = productId
                )
            )
        }
    }
}