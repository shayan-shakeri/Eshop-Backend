package com.shayan.feature.auth.dto

data class UserAuthResponse(
    val accessToken: String,
    val refreshToken: String
)

