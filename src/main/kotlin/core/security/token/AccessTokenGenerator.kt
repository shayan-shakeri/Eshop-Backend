package core.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.AppConstants
import core.consts.CJWT
import core.consts.ENV
import core.util.EnvLoader
import java.util.Date

object AccessTokenGenerator {

    private fun algorithm(): Algorithm {

        val secret = EnvLoader.get(
            ENV.ACCESS_SECRET
        )

        return Algorithm.HMAC256(secret)
    }

    fun userToken(
        id: String
    ): String {

        val issuer = EnvLoader.get(
            ENV.TOKEN_ISSUER
        )

        val audience = EnvLoader.get(
            ENV.TOKEN_AUDIENCE
        )

        val exp = EnvLoader.getLong(
            ENV.ACCESS_USER_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim(CJWT.CLAIM_ID, id)
            .withClaim(CJWT.CLAIM_EMPLOYEE, false)
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

    fun employeeToken(
        id: String,
        roleId: String
    ): String {

        val issuer = EnvLoader.get(
            ENV.TOKEN_ISSUER
        )

        val audience = EnvLoader.get(
            ENV.TOKEN_AUDIENCE
        )

        val exp = EnvLoader.getLong(
            ENV.ACCESS_EMPLOYEE_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withClaim(CJWT.CLAIM_ID, id)
            .withClaim(CJWT.CLAIM_ROLE, roleId)
            .withClaim(CJWT.CLAIM_EMPLOYEE, true)
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

}
