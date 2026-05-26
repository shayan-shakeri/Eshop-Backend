package com.shayan.feature.users_session.repository

import com.shayan.feature.users_session.model.UserSession

interface UserSessionRepository {
    suspend fun getSessionByUserId(userId: String): UserSession?
    suspend fun getTokenByHash(hash: String): UserSession?

    suspend fun addSession(userSession: UserSession): UserSession?

    suspend fun updateLastUsed(userId: String): UserSession?

    suspend fun revokeSession(userId: String)
}