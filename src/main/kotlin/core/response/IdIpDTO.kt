package com.shayan.core.response

import kotlinx.serialization.Serializable

@Serializable
data class IdIpDTO (
    val id: String,
    val ip: String
)