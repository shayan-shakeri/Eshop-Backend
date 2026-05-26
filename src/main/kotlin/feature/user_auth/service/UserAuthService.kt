package com.shayan.feature.user_auth.service

import com.shayan.feature.auth.dto.UserAuthResponse
import com.shayan.feature.users_session.service.UserSessionService
import core.security.token.AccessTokenGenerator

class UserAuthService(
    private val userSessionService: UserSessionService
) {

    suspend fun generateAccessToken(token: String): String {
        val userSession = userSessionService.verify(token)
        val updateResponse = userSessionService.updateLastUsed(userSession.userId)
        return AccessTokenGenerator.userToken(updateResponse.userId)
    }

    suspend fun generateRefreshToken(userId: String, deviceId: String): UserAuthResponse{
        val userSession = userSessionService.getSessionByUserId(userId)
        val userSessionResponse = userSession ?: userSessionService.addSession(userId, deviceId)
        val token = AccessTokenGenerator.userToken(userSessionResponse.userId)
        return UserAuthResponse(token, userSessionResponse.refreshToken)
    }

    suspend fun revokeRefreshToken(userId: String){
        userSessionService.revokeSession(userId)
    }

}