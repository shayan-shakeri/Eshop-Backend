package com.shayan.feature.category.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val id: String,
    val name: String,
    val url: String
)
