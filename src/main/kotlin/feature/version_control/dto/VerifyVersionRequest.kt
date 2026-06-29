package com.shayan.feature.version_control.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyVersionRequest(
    val version: String
)