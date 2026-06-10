package core.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import core.consts.AppConstants
import core.consts.ENV
import core.util.EnvLoader

object RefreshTokenVerifier {

    private fun algorithm(): Algorithm {

        val secret = EnvLoader.get(
            ENV.REFRESH_SECRET
        )

        return Algorithm.HMAC256(secret)
    }

    fun verify(token: String): DecodedJWT {

        val issuer = EnvLoader.get(
            ENV.TOKEN_ISSUER
        )

        val verifier = JWT.require(algorithm())
            .withIssuer(issuer)
            .build()

        return verifier.verify(token)
    }

}
