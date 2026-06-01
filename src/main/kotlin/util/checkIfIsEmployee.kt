package com.shayan.util

import core.consts.AppConstants
import core.exception.AuthenticationException
import core.exception.Forbidden
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun ApplicationCall.checkIfIsEmployee() {
    val principal = this.principal<JWTPrincipal>() ?: throw AuthenticationException(AppConstants.Exception.INVALID_TOKEN)

    val isEmployee = principal.payload.getClaim(AppConstants.Jwt.CLAIM_EMPLOYEE).asBoolean() == true

    if (!isEmployee) {
        throw Forbidden(onlyEmployeeHasAccess = true)
    }
}