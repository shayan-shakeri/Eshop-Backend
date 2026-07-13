package com.shayan.feature.monthly_log.repository

import com.shayan.feature.monthly_log.model.MonthlyLog

interface MonthlyLogRepository {

    suspend fun add(
        monthlyLog: MonthlyLog
    ): MonthlyLog?

    suspend fun findById(
        id: String
    ): MonthlyLog?

    suspend fun findByMonthAndYear(
        month: Int,
        year: Int
    ): MonthlyLog?

    suspend fun readAll():
            List<MonthlyLog>
}