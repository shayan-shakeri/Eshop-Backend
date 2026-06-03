package com.shayan.feature.address.model

data class Address (
    val id: String,
    val userId: String,
    val country: String,
    val state: String?,
    val city: String,
    val road: String,
    val postalCode: String,
    val additionalInfo: String?,
    val mainAddress: Boolean
)