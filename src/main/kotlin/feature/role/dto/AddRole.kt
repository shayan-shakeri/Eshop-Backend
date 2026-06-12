package com.shayan.feature.role.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddRole(
    val title: String,
    val description: String,
    val code: Int,
    val ip: String
)
