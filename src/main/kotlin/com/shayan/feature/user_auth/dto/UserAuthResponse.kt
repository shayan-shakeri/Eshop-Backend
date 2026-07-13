package com.shayan.feature.user_auth.dto

data class UserAuthResponse(
    val accessToken: String,
    val refreshToken: String
)

