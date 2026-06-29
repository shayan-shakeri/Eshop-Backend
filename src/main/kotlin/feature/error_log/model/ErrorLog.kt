package com.shayan.feature.error_log.model

import java.time.Instant


data class ErrorLog(
    val id: String,
    val errorMessage: String,
    val createdAt: Instant
)