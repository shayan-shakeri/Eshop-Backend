package com.shayan.feature.users.model

import com.shayan.util.enum.Gender
import java.time.LocalDate

data class Users(
    val id: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val gender: Gender,
    val birthday: LocalDate,
    val passwordHash: String,
    val iterations: Int,
    val algorithm: String,
    val salt: String
)
