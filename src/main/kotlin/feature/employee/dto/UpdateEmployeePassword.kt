package com.shayan.feature.employee.dto

data class UpdateEmployeePassword (
    val id: String,
    val newPassword: String,
    val ip: String
)