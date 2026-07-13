package com.shayan.feature.search_history.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ReadSearchHistory (
    val id: String,
    val userId: String,
    val content: String,
    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)