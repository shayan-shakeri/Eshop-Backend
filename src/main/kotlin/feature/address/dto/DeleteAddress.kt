package com.shayan.feature.address.dto

import kotlinx.serialization.Serializable

@Serializable
data class DeleteAddress (
    val id: String,
    val ip: String,
    val userId: String
)