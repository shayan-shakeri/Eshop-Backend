package com.shayan.feature.error_log.mapper

import com.shayan.feature.error_log.model.ErrorLog
import com.shayan.feature.error_log.table.ErrorLogTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toErrorLog() =
    ErrorLog(
        id = this[ErrorLogTable.id],
        errorMessage = this[
            ErrorLogTable.errorMessage
        ],
        createdAt = this[
            ErrorLogTable.createdAt
        ]
    )