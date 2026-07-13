package com.shayan.feature.users.mapper

import com.shayan.feature.users.dto.UserResponse
import com.shayan.feature.users.model.Users

fun Users.toUserResponse(
    accessToken: String,
    refreshToken: String
) = UserResponse(
    id = this.id,
    fullName = this.fullName,
    email = this.email,
    phoneNumber = this.phoneNumber,
    gender = this.gender,
    birthday = this.birthday,
    accessToken = accessToken,
    refreshToken = refreshToken
)