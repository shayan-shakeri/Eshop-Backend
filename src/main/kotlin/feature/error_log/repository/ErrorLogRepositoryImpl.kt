package com.shayan.feature.error_log.repository

import com.shayan.feature.error_log.mapper.toErrorLog
import com.shayan.feature.error_log.model.ErrorLog
import com.shayan.feature.error_log.table.ErrorLogTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ErrorLogRepositoryImpl :
    ErrorLogRepository {

    override suspend fun add(
        errorLog: ErrorLog
    ): ErrorLog? {

        ErrorLogTable.insert {
            it[id] = errorLog.id
            it[errorMessage] =
                errorLog.errorMessage
        }

        return findById(
            errorLog.id
        )
    }

    override suspend fun findById(
        id: String
    ): ErrorLog? =
        ErrorLogTable
            .selectAll()
            .where {
                ErrorLogTable.id eq id
            }
            .singleOrNull()
            ?.toErrorLog()

    override suspend fun readAll():
            List<ErrorLog> =
        ErrorLogTable
            .selectAll()
            .orderBy(
                ErrorLogTable.createdAt,
                SortOrder.DESC
            )
            .map {
                it.toErrorLog()
            }

    override suspend fun delete(
        id: String
    ) {

        ErrorLogTable.deleteWhere {
            ErrorLogTable.id eq id
        }
    }

    override suspend fun deleteAll() {

        ErrorLogTable.deleteAll()
    }
}