package com.shayan.feature.search_history.model

import java.time.Instant

data class SearchHistory (
    val id: String,
    val userId: String,
    val content: String,
    val createdAt: Instant
)