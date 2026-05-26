package com.shayan.feature.users_session.repository

import com.shayan.feature.users_session.table.UserSessionTable
import com.shayan.feature.users_session.mapper.toUserSession
import com.shayan.feature.users_session.model.UserSession
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.time.Instant

class UserSessionRepositoryImpl : UserSessionRepository {
    override suspend fun getSessionByUserId(userId: String): UserSession? =
        UserSessionTable
            .selectAll()
            .where((UserSessionTable.userId eq userId) and (UserSessionTable.revoked eq false))
            .singleOrNull()
            ?.toUserSession()

    override suspend fun getTokenByHash(hash: String): UserSession? =
        UserSessionTable
            .selectAll()
            .where((UserSessionTable.refreshTokenHash eq hash) and (UserSessionTable.revoked eq false))
            .singleOrNull()
            ?.toUserSession()


    override suspend fun addSession(userSession: UserSession): UserSession? {

        UserSessionTable.insert {
            it[UserSessionTable.id] = userSession.id
            it[UserSessionTable.userId] = userSession.userId
            it[UserSessionTable.refreshTokenHash] = userSession.refreshToken
            it[UserSessionTable.deviceId] = userSession.deviceId
            it[UserSessionTable.expireAt] = userSession.expiresAt
            it[UserSessionTable.revoked] = userSession.revoked
            it[UserSessionTable.createdAt] = userSession.createdAt
            it[UserSessionTable.lastUsedAt] = userSession.lastUsedAt
        }

        return UserSessionTable
            .selectAll()
            .where(UserSessionTable.id eq userSession.id)
            .singleOrNull()
            ?.toUserSession()
    }

    override suspend fun updateLastUsed(userId: String): UserSession? {
        UserSessionTable.update({ UserSessionTable.userId eq userId }) {
            it[UserSessionTable.lastUsedAt] = Instant.now()
        }
        return UserSessionTable
            .selectAll()
            .where((UserSessionTable.userId eq userId) and (UserSessionTable.revoked eq false))
            .singleOrNull()
            ?.toUserSession()
    }

    override suspend fun revokeSession(userId: String) {
        UserSessionTable.update({ UserSessionTable.userId eq userId }) {
            it[UserSessionTable.revoked] = false
        }
    }
}