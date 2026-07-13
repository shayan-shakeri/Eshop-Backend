package com.shayan.feature.role.dto

import kotlinx.serialization.Serializable

@Serializable
data class RoleResponse (
    val id: String,
    val title: String,
    val description: String,
    val code: Int
)