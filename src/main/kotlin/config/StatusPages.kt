package core.plugin

import com.shayan.core.exception.AlreadyExistsException
import com.shayan.core.exception.EmailExist
import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.ImageExist
import com.shayan.core.exception.InvalidCredentials
import com.shayan.feature.error_log.service.ErrorLogService
import core.consts.ASP
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
                cause.message ?: ASP.UNAUTHORIZED
            )
        }

        exception<InvalidCredentials> { call, cause ->
            call.respond(
                HttpStatusCode.Unauthorized,
                cause.message ?: ASP.INVALID_CREDENTIALS
            )
        }

        exception<Forbidden> { call, cause ->
            call.respond(
                HttpStatusCode.Forbidden,
                cause.message ?: ASP.FORBIDDEN
            )
        }

        exception<NotFoundException> { call, cause ->
            call.respond(
                HttpStatusCode.NotFound,
                cause.message ?: ASP.NOT_FOUND
            )
        }

        exception<BadRequestException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                cause.message ?: ASP.BAD_REQUEST
            )
        }

        exception<EmailExist> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                cause.message ?: ASP.EMAIL_EXISTS
            )
        }

        exception<ImageExist> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                cause.message ?: ASP.IMAGE_EXISTS
            )
        }

        exception<FailedToAdd> { call, cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                cause.message ?: ASP.FAILED_TO_ADD
            )
        }

        exception<AlreadyExistsException> { call, cause ->
            call.respond(
                HttpStatusCode.Conflict,
                cause.message ?: ASP.ALREADY_EXISTS
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