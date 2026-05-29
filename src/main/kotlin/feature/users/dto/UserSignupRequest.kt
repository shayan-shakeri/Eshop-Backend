package com.shayan.feature.users.dto

import com.shayan.util.Gender
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserSignupRequest(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val gender: Gender,
    @Contextual val birthday: LocalDate,
    val password: String,
    val deviceId: String,
    val ip: String
)
