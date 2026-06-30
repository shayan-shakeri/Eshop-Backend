package com.shayan.feature.version_control.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class VersionControlResponse(
    val id: String,
    val version: String,
    val active: Boolean,
    val description: String,
    val forced: Boolean,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)