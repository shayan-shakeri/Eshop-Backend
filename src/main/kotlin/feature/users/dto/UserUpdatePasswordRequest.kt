package com.shayan.feature.users.dto

data class UserUpdatePasswordRequest (
    val oldPassword: String,
    val newPassword: String,
    val ip: String
)