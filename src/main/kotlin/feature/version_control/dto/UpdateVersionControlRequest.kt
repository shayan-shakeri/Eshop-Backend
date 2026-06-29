package com.shayan.feature.version_control.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateVersionControlRequest(
    val id: String,
    val version: String,
    val active: Boolean,
    val description: String,
    val forced: Boolean,
    val ip: String
)