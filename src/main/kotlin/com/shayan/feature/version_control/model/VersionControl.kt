package com.shayan.feature.version_control.model

import java.time.Instant


data class VersionControl(
    val id: String,
    val version: String,
    val active: Boolean,
    val description: String,
    val forced: Boolean,
    val createdAt: Instant
)