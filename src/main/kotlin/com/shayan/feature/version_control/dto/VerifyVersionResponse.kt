package com.shayan.feature.version_control.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyVersionResponse(
    val supported: Boolean,
    val forced: Boolean,
    val latestVersion: String,
    val description: String
)