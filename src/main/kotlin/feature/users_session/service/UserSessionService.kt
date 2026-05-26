package com.shayan.feature.users_session.service

import com.shayan.feature.users_session.constants.UserSessionConst
import com.shayan.feature.users_session.model.UserSession
import com.shayan.feature.users_session.repository.UserSessionRepository
import core.database.dbQuery
import core.security.token.RefreshTokenGenerator
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import java.time.Clock
import java.time.Instant

class UserSessionService(
    private val userSessionRepo: UserSessionRepository,
    private val clock: Clock = Clock.systemDefaultZone()
){

    suspend fun addSession(userId: String, deviceId: String): UserSession =
        dbQuery {
            val id  = IdGenerator.generate()
            val now  = Instant.now(clock)

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
            userSessionRepo.addSession(userSession) ?: throw NotFoundException()
        }

    suspend fun updateLastUsed(userId: String): UserSession =
        dbQuery {
            userSessionRepo.updateLastUsed(userId) ?: throw NotFoundException()
        }

    suspend fun revokeSession(userId: String) {
        dbQuery{ userSessionRepo.revokeSession(userId) }
    }

    suspend fun getSessionByUserId(userId: String): UserSession? =
        dbQuery {
            userSessionRepo.getSessionByUserId(userId)
        }

    suspend fun verify(hash: String): UserSession =
        dbQuery {
            userSessionRepo.getTokenByHash(hash) ?: throw NotFoundException()
        }

}