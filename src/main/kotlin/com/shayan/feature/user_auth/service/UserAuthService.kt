package com.shayan.feature.user_auth.service

import com.shayan.feature.user_auth.dto.UserAuthResponse
import com.shayan.feature.users_session.service.UserSessionService
import core.database.dbQuery
import core.exception.AuthenticationException
import core.security.token.AccessTokenGenerator

class UserAuthService(
    private val userSessionService: UserSessionService
) {

    suspend fun generateAccessToken(token: String): String =
        dbQuery {
            val session = userSessionService.verify(token)

            val userId = session.userId
                ?: throw AuthenticationException("Invalid refresh token")

            userSessionService.updateLastUsed(userId)

            AccessTokenGenerator.userToken(userId)
        }

    suspend fun generateRefreshToken(
        userId: String,
        deviceId: String
    ): UserAuthResponse {

        val session =
            userSessionService.getSessionByUserId(userId)
                ?: userSessionService.addSession(userId, deviceId)

        return UserAuthResponse(
            accessToken = AccessTokenGenerator.userToken(userId),
            refreshToken = session.refreshToken
        )
    }

    suspend fun revokeRefreshToken(userId: String) {
        userSessionService.revokeSession(userId)
    }
}