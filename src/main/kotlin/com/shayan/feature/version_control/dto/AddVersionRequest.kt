package com.shayan.feature.version_control.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddVersionRequest(
    val version: String,
    val active: Boolean,
    val description: String,
    val forced: Boolean,
    val ip: String
)