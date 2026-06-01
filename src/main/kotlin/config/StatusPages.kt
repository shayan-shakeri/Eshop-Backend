package com.shayan.config

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.InvalidCredentials
import core.response.ApiResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configStatusPages() {
    install(StatusPages) {

        exception<InvalidCredentials> { call, _ ->
            call.respond(
                HttpStatusCode.Unauthorized,
                ApiResponse(
                    success = false,
                    message = "Invalid credentials"
                )
            )
        }

        exception<FailedToAdd> { call, _ ->
            call.respond(
                HttpStatusCode.BadRequest,
                ApiResponse(
                    success = false,
                    message = "Failed to add user"
                )
            )
        }

        exception<Throwable> { call, cause ->
            cause.printStackTrace()

            call.respond(
                HttpStatusCode.InternalServerError,
                ApiResponse(
                    success = false,
                    message = "Internal server error"
                )
            )
        }

    }
}