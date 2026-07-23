package com.shayan.feature.user_auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRefreshRequest (
    val refreshToken: String
)