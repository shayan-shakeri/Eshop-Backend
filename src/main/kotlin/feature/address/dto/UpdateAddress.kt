package com.shayan.feature.address.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateAddress (
        val id: String,
        val country: String,
        val state: String?,
        val city: String,
        val road: String,
        val postalCode: String,
        val additionalInfo: String?,
        val ip: String
)