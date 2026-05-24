package core.util


import core.consts.AppConstants
import io.ktor.server.application.ApplicationCall

fun ApplicationCall.extractFromParam(paramName: String): String =
    this.parameters[paramName] ?: throw IllegalArgumentException(AppConstants.Exception.MISSING_PARAM)
