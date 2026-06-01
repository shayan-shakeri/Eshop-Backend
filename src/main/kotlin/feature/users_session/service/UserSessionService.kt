package com.shayan.feature.users_session.service

import com.shayan.feature.users_session.constants.UserSessionConst
import com.shayan.feature.users_session.model.UserSession
import com.shayan.feature.users_session.repository.UserSessionRepository
import core.database.dbQuery
import core.exception.AuthenticationException
import core.security.token.RefreshTokenGenerator
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import java.time.Clock
import java.time.Instant

class UserSessionService(
    private val userSessionRepo: UserSessionRepository,
    private val clock: Clock = Clock.systemDefaultZone()
){

    suspend fun addSession(userId: String, deviceId: String): UserSession {
        val id = IdGenerator.generate()
        val now = Instant.now(clock)

        val userSession = UserSession(
            id = id,
            userId = userId,
            refreshToken = RefreshTokenGenerator.generate(id, false),
            deviceId = deviceId,
            expiresAt = now.plusSeconds(UserSessionConst.PLUS_SECOND),
            revoked = false,
            createdAt = now,
            lastUsedAt = now
        )
        return userSessionRepo.addSession(userSession) ?: throw NotFoundException()
    }


    suspend fun updateLastUsed(userId: String): UserSession =
            userSessionRepo.updateLastUsed(userId) ?: throw NotFoundException()


    suspend fun revokeSession(userId: String) {
         userSessionRepo.revokeSession(userId)
    }

    suspend fun getSessionByUserId(userId: String): UserSession? =

            userSessionRepo.getSessionByUserId(userId)


    suspend fun verify(hash: String): UserSession {
            val session =
                userSessionRepo.getTokenByHash(hash)
                    ?: throw NotFoundException()

            if (session.expiresAt.isBefore(Instant.now(clock))) {
            userSessionRepo.revokeSession(session.userId ?: throw AuthenticationException("Invalid refresh token"))
                throw NotFoundException()
            }

            return session
        }

}