package com.shayan.feature.error_log.mapper

import com.shayan.feature.error_log.dto.ErrorLogResponse
import com.shayan.feature.error_log.model.ErrorLog

fun ErrorLog.toErrorLogResponse() =
    ErrorLogResponse(
        id = id,
        errorMessage = errorMessage,
        createdAt = createdAt
    )