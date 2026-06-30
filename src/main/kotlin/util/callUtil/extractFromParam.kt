package core.util


import core.consts.EXC
import io.ktor.server.application.*

fun ApplicationCall.extractFromParam(paramName: String): String =
    this.parameters[paramName] ?: throw IllegalArgumentException(EXC.MISSING_PARAM)
