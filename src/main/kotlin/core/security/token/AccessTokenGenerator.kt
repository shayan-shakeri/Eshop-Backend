package core.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.AppConstants
import core.util.EnvLoader
import java.util.Date

object AccessTokenGenerator {

    private fun algorithm(): Algorithm {

        val secret = EnvLoader.get(
            AppConstants.Env.ACCESS_SECRET
        )

        return Algorithm.HMAC256(secret)
    }

    fun userToken(
        id: String
    ): String {

        val issuer = EnvLoader.get(
            AppConstants.Env.TOKEN_ISSUER
        )

        val audience = EnvLoader.get(
            AppConstants.Env.TOKEN_AUDIENCE
        )

        val exp = EnvLoader.getLong(
            AppConstants.Env.ACCESS_USER_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim(AppConstants.Jwt.CLAIM_ID, id)
            .withClaim(AppConstants.Jwt.CLAIM_EMPLOYEE, false)
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

    fun employeeToken(
        id: String,
        roleId: String
    ): String {

        val issuer = EnvLoader.get(
            AppConstants.Env.TOKEN_ISSUER
        )

        val audience = EnvLoader.get(
            AppConstants.Env.TOKEN_AUDIENCE
        )

        val exp = EnvLoader.getLong(
            AppConstants.Env.ACCESS_EMPLOYEE_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim(AppConstants.Jwt.CLAIM_ID, id)
            .withClaim(AppConstants.Jwt.CLAIM_ROLE, roleId)
            .withClaim(AppConstants.Jwt.CLAIM_EMPLOYEE, true)
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

}
