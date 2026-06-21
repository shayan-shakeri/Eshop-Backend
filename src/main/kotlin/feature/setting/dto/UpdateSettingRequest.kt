package com.shayan.feature.setting.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateSettingRequest(
    val name: String,
    val value: String
)