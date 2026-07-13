package core.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.AppConstants
import core.consts.CJWT
import core.consts.ENV
import core.util.EnvLoader
import core.util.IdGenerator
import java.util.Date

object RefreshTokenGenerator {

    private fun algorithm(): Algorithm {

        val secret = EnvLoader.get(
            ENV.REFRESH_SECRET
        )

        return Algorithm.HMAC256(secret)
    }

    fun generate(
        id: String,
        employee: Boolean = false
    ): String {

        val issuer = EnvLoader.get(
            ENV.TOKEN_ISSUER
        )

        val exp = EnvLoader.getLong(
            ENV.REFRESH_EXP
        )

        val now = System.currentTimeMillis()

        return JWT.create()
            .withIssuer(issuer)
            .withClaim(CJWT.CLAIM_ID, id)
            .withClaim(CJWT.CLAIM_EMPLOYEE, employee)
            .withClaim(CJWT.CLAIM_JTI, IdGenerator.generate())
            .withExpiresAt(Date(now + exp))
            .sign(algorithm())
    }

}
