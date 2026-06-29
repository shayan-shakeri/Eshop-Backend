package com.shayan.feature.error_log.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant


@Serializable
data class ErrorLogResponse(
    val id: String,
    val errorMessage: String,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)