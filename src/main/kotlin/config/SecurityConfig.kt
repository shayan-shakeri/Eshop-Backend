package config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import core.consts.AppConstants
import core.exception.AuthenticationException
import core.util.EnvLoader
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    authentication {

        jwt(AppConstants.Jwt.ACCESS_AUTH) {

            realm = EnvLoader.get(AppConstants.Env.TOKEN_ISSUER)

            verifier(
                JWT.require(
                    Algorithm.HMAC256(
                        EnvLoader.get(AppConstants.Env.ACCESS_SECRET)
                    )
                )
                    .withIssuer(
                        EnvLoader.get(AppConstants.Env.TOKEN_ISSUER)
                    )
                    .withAudience(
                        EnvLoader.get(AppConstants.Env.TOKEN_AUDIENCE)
                    )
                    .build()
            )

            validate { credential ->
                val id = credential.payload.getClaim(AppConstants.Jwt.CLAIM_ID).asString()
                if (id.isNullOrBlank()) {
                    null
                } else {
                    JWTPrincipal(credential.payload)
                }
            }

            challenge { _, _ ->
                throw AuthenticationException("Unauthorized")
            }
        }
    }
}
