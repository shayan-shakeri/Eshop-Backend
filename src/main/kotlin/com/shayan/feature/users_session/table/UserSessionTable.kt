package com.shayan.feature.users_session.table

import com.shayan.feature.users.table.UsersTable
import com.shayan.feature.users_session.constants.UserSessionConst
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object UserSessionTable: Table(UserSessionConst.TABLE_NAME) {
    val id = varchar(UserSessionConst.ID, ANC.ID_LENGTH)
    val userId = varchar(UserSessionConst.USER_ID, ANC.ID_LENGTH).references(
        UsersTable.id,
        onUpdate = ReferenceOption.RESTRICT,
        onDelete = ReferenceOption.SET_NULL
    ).nullable()
    val refreshTokenHash = varchar(UserSessionConst.REFRESH_TOKEN_HASH, UserSessionConst.REFRESH_TOKEN_LENGTH)
    val deviceId = varchar(UserSessionConst.DEVICE_ID, UserSessionConst.DEVICE_ID_LENGTH)
    val expireAt = timestamp(UserSessionConst.EXPIRE_AT)
    val revoked = bool(UserSessionConst.REVOKED)
    val createdAt = timestamp(UserSessionConst.CREATED_AT)
    val lastUsedAt = timestamp(UserSessionConst.LAST_USED_AT).nullable()

    override val primaryKey = PrimaryKey(id)
}