package core.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.AppConstants
import core.util.EnvLoader
import core.util.IdGenerator
import java.util.Date

object RefreshTokenGenerator {

    private fun algorithm(): Algorithm {

        val secret = EnvLoader.get(
            AppConstants.Env.REFRESH_SECRET
        )

        return Algorithm.HMAC256(secret)
    }

    fun generate(
        id: String,
        employee: Boolean = false
    ): String {

        val issuer = EnvLoader.get(
            AppConstants.Env.TOKEN_ISSUER
        )

        val exp = EnvLoader.getLong(
            AppConstants.Env.REFRESH_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withClaim(AppConstants.Jwt.CLAIM_ID, id)
            .withClaim(AppConstants.Jwt.CLAIM_EMPLOYEE, employee)
            .withClaim(AppConstants.Jwt.CLAIM_JTI, IdGenerator.generate())
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

}
