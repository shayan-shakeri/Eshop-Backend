package com.shayan.feature.setting.dto

import kotlinx.serialization.Serializable

@Serializable
data class SettingResponse(
    val id: String,
    val productId: String,
    val name: String,
    val value: String
)