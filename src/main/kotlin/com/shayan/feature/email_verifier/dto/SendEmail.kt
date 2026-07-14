package com.shayan.feature.email_verifier.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendEmail(
    val email: String
)
