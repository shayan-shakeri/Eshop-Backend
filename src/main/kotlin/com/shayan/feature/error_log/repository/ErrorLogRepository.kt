package com.shayan.feature.error_log.repository

import com.shayan.feature.error_log.model.ErrorLog

interface ErrorLogRepository {

    suspend fun add(
        errorLog: ErrorLog
    ): ErrorLog?

    suspend fun findById(
        id: String
    ): ErrorLog?

    suspend fun readAll():
            List<ErrorLog>

    suspend fun delete(
        id: String
    )

    suspend fun deleteAll()
}