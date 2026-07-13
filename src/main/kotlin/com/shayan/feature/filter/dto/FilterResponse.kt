package com.shayan.feature.filter.dto

import kotlinx.serialization.Serializable

@Serializable
data class FilterResponse (
    val id: String,
    val categoryId: String,
    val name: String,
    val url: String
)