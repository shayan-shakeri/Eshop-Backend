package com.shayan.feature.users.dto

import com.shayan.util.enum.Gender
import com.shayan.util.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserUpdateInfoRequest(
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val gender: Gender,
    @Serializable(with = LocalDateSerializer::class)
    val birthday: LocalDate,
    val ip: String
)