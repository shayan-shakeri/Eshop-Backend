package com.shayan.util.callUtil

import core.consts.AppConstants
import core.consts.CJWT
import core.consts.EXC
import core.exception.AuthenticationException
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal

fun ApplicationCall.idExtractor(): String {
    val principal = this.principal<JWTPrincipal>() ?: throw AuthenticationException(EXC.INVALID_TOKEN)

    return principal.payload.getClaim(CJWT.CLAIM_ID).asString()
}