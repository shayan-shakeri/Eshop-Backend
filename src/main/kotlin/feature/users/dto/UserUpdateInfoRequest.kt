package com.shayan.feature.users.dto

import com.shayan.util.Gender
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserUpdateInfoRequest(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val gender: Gender,
    @Contextual val birthday: LocalDate,
    val ip: String
)