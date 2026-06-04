package com.shayan.feature.search_history.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddSearchHistory (
    val userId: String,
    val content: String
)
