package com.shayan.util.callUtil

import core.consts.AppConstants
import core.exception.AuthenticationException
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal

fun ApplicationCall.idExtractor(): String {
    val principal = this.principal<JWTPrincipal>() ?: throw AuthenticationException(AppConstants.Exception.INVALID_TOKEN)

    return principal.payload.getClaim(AppConstants.Jwt.CLAIM_ID).asString()
}