package core.plugin

import com.shayan.core.exception.EmailExist
import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.ImageExist
import com.shayan.core.exception.InvalidCredentials
import com.shayan.feature.error_log.service.ErrorLogService
import core.exception.AuthenticationException
import core.exception.Forbidden
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages(
    errorLogService: ErrorLogService
) {

    install(StatusPages) {

        exception<AuthenticationException> { call, cause ->
            call.respond(
                HttpStatusCode.Unauthorized,
                cause.message ?: "Unauthorized"
            )
        }

        exception<InvalidCredentials> { call, cause ->
            call.respond(
                HttpStatusCode.Unauthorized,
                cause.message ?: "Invalid credentials"
            )
        }

        exception<Forbidden> { call, cause ->
            call.respond(
                HttpStatusCode.Forbidden,
                cause.message ?: "Forbidden"
            )
        }

        exception<NotFoundException> { call, cause ->
            call.respond(
                HttpStatusCode.NotFound,
                cause.message ?: "Not found"
            )
        }

        exception<BadRequestException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                cause.message ?: "Bad request"
            )
        }

        exception<EmailExist> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                cause.message ?: "Email already exists"
            )
        }

        exception<ImageExist> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                cause.message ?: "Image already exists"
            )
        }

        exception<FailedToAdd> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                cause.message ?: "Failed to add resource"
            )
        }

        exception<Throwable> { call, cause ->

            runCatching {
                errorLogService.add(
                    cause.stackTraceToString()
                )
            }

            this@configureStatusPages.environment.log.error(
                cause.stackTraceToString()
            )

            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf(
                    "message" to "Internal server error"
                )
            )
        }
    }
}