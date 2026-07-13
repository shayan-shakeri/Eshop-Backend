package com.shayan.feature.users.mapper

import com.shayan.feature.users.dto.UserNoTokenResponse
import com.shayan.feature.users.model.Users
fun Users.toUserNoTokenResponse(): UserNoTokenResponse =
    UserNoTokenResponse(
        id = this.id,
        fullName = this.fullName,
        email = this.email,
        phoneNumber = this.phoneNumber,
        gender = this.gender,
        birthday = this.birthday
    )