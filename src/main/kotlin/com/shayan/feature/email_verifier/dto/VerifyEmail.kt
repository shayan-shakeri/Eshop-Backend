package com.shayan.feature.email_verifier.dto

import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmail (
    val email: String,
    val code: String
)
