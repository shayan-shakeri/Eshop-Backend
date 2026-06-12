package com.shayan.util.jwt

import core.consts.CJWT
import core.consts.EXC
import core.exception.AuthenticationException
import core.exception.Forbidden
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.checkIfIsEmployee() {
    val principal = this.principal<JWTPrincipal>() ?: throw AuthenticationException(EXC.INVALID_TOKEN)

    val isEmployee = principal.payload.getClaim(CJWT.CLAIM_EMPLOYEE).asBoolean() == true

    if (!isEmployee) {
        throw Forbidden(onlyEmployeeHasAccess = true)
    }
}