package com.shayan.feature.version_control.dto

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class VersionControlResponse(
    val id: String,
    val version: String,
    val active: Boolean,
    val description: String,
    val forced: Boolean,
    val createdAt: Instant
)