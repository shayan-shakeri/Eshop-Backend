package com.shayan.feature.product.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateStock (
    val id: String,
    val ip: String,
    val amount: Int
)