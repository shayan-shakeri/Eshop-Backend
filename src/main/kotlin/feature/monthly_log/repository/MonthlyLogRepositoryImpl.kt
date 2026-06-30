package com.shayan.feature.monthly_log.repository

import com.shayan.feature.monthly_log.mapper.toMonthlyLog
import com.shayan.feature.monthly_log.model.MonthlyLog
import com.shayan.feature.monthly_log.table.MonthlyLogTable
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class MonthlyLogRepositoryImpl :
    MonthlyLogRepository {

    override suspend fun add(
        monthlyLog: MonthlyLog
    ): MonthlyLog? {

        MonthlyLogTable.insert {

            it[id] =
                monthlyLog.id

            it[month] =
                monthlyLog.month

            it[year] =
                monthlyLog.year

            it[goods] =
                monthlyLog.goods

            it[salary] =
                monthlyLog.salary

            it[insurance] =
                monthlyLog.insurance

            it[debt] =
                monthlyLog.debt

            it[debtPaid] =
                monthlyLog.debtPaid

            it[other] =
                monthlyLog.other

            it[finalExpenses] =
                monthlyLog.finalExpenses

            it[finalProfit] =
                monthlyLog.finalProfit

            it[totalSum] =
                monthlyLog.totalSum
        }

        return findById(
            monthlyLog.id
        )
    }

    override suspend fun findById(
        id: String
    ): MonthlyLog? =
        MonthlyLogTable
            .selectAll()
            .where {
                MonthlyLogTable.id eq id
            }
            .singleOrNull()
            ?.toMonthlyLog()

    override suspend fun findByMonthAndYear(
        month: Int,
        year: Int
    ): MonthlyLog? =
        MonthlyLogTable
            .selectAll()
            .where {
                (MonthlyLogTable.month eq month) and
                        (MonthlyLogTable.year eq year)
            }
            .singleOrNull()
            ?.toMonthlyLog()

    override suspend fun readAll():
            List<MonthlyLog> =
        MonthlyLogTable
            .selectAll()
            .orderBy(
                MonthlyLogTable.year,
                SortOrder.DESC
            )
            .orderBy(
                MonthlyLogTable.month,
                SortOrder.DESC
            )
            .map {
                it.toMonthlyLog()
            }
}