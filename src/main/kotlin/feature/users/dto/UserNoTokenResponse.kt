package com.shayan.feature.users.dto

import com.shayan.util.Gender
import com.shayan.util.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class UserNoTokenResponse(
    val id: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val gender: Gender,
    @Serializable(with = LocalDateSerializer::class)
    val birthday: LocalDate
)
