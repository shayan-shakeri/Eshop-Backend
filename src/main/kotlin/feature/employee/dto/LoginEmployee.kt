package com.shayan.feature.employee.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginEmployee (
    val email: String,
    val password: String,
    val ip: String
)