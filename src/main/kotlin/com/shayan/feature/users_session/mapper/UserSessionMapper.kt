package com.shayan.feature.users_session.mapper

import com.shayan.feature.users_session.table.UserSessionTable
import com.shayan.feature.users_session.model.UserSession
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUserSession(): UserSession = UserSession(
    id = this[UserSessionTable.id],
    userId = this[UserSessionTable.userId],
    refreshToken = this[UserSessionTable.refreshTokenHash],
    deviceId = this[UserSessionTable.deviceId],
    expiresAt = this[UserSessionTable.expireAt],
    revoked = this[UserSessionTable.revoked],
    createdAt = this[UserSessionTable.createdAt],
    lastUsedAt = this[UserSessionTable.lastUsedAt]
)