package com.shayan.feature.users_session.constants

object UserSessionConst {
    const val TABLE_NAME = "user_session"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val REFRESH_TOKEN_HASH = "refresh_token_hash"
    const val DEVICE_ID = "device_id"
    const val EXPIRE_AT = "expire_at"
    const val REVOKED = "revoked"
    const val CREATED_AT = "created_at"
    const val LAST_USED_AT = "last_used_at"

    const val REFRESH_TOKEN_LENGTH = 255
    const val DEVICE_ID_LENGTH = 255
    const val PLUS_SECOND: Long = 60 * 60 * 24 *7

}