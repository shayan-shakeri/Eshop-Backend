package com.shayan.feature.users.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val email: String,
    val passwordHash: String,
    val deviceId: String,
    val ip: String
    )
