package config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.CJWT
import core.consts.ENV
import core.consts.EXC
import core.exception.AuthenticationException
import core.util.EnvLoader
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    authentication {

        jwt(CJWT.ACCESS_AUTH) {

            realm = EnvLoader.get(ENV.TOKEN_ISSUER)

            verifier(
                JWT.require(
                    Algorithm.HMAC256(
                        EnvLoader.get(ENV.ACCESS_SECRET)
                    )
                )
                    .withIssuer(
                        EnvLoader.get(ENV.TOKEN_ISSUER)
                    )
                    .withAudience(
                        EnvLoader.get(ENV.TOKEN_AUDIENCE)
                    )
                    .build()
            )

            validate { credential ->
                val id = credential.payload.getClaim(CJWT.CLAIM_ID).asString()
                if (id.isNullOrBlank()) {
                    null
                } else {
                    JWTPrincipal(credential.payload)
                }
            }

            challenge { _, _ ->
                throw AuthenticationException(EXC.UNAUTHORIZED)
            }

        }
    }
}
