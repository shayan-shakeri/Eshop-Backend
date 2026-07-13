package com.shayan.feature.email_verifier.route

import com.shayan.feature.email_verifier.dto.SendEmail
import com.shayan.feature.email_verifier.dto.VerifyEmail
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.sender.constants.EmailVerifierConst
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.emailVerifierRoute(
    emailVerifierService: EmailVerifierService
) {
    route(EmailVerifierConst.MAIN_ROUTE){
        post(EmailVerifierConst.SEND_ROUTE) {
            val receive = call.receive<SendEmail>()
            call.respond(emailVerifierService.send(receive))
        }

        post(EmailVerifierConst.VERIFY_ROUTE) {
            val receive = call.receive<VerifyEmail>()
            call.respond(emailVerifierService.verify(receive))
        }
    }

}