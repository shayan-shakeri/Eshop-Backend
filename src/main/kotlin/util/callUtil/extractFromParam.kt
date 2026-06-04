package core.util


import core.consts.AppConstants
import core.consts.EXC
import io.ktor.server.application.ApplicationCall

fun ApplicationCall.extractFromParam(paramName: String): String =
    this.parameters[paramName] ?: throw IllegalArgumentException(EXC.MISSING_PARAM)
