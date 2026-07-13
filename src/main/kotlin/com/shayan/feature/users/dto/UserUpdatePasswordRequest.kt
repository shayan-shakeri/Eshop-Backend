package com.shayan.feature.users.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdatePasswordRequest (
    val oldPassword: String,
    val newPassword: String,
    val ip: String
)