package com.shayan.feature.employee.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateEmployeePassword (
    val id: String,
    val newPassword: String,
    val ip: String
)