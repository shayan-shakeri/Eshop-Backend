package com.shayan.feature.setting.dto

import kotlinx.serialization.Serializable

@Serializable
data class AddSettingRequest(
    val productId: String,
    val name: String,
    val value: String,
    val ip: String
)