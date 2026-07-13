package com.shayan.feature.email_verifier.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendEmail(
    val email: String,
    val userId: String,
    val username: String,
    val ip: String
)
