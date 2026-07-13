package com.shayan.feature.monthly_log.mapper

import com.shayan.feature.monthly_log.model.MonthlyLog
import com.shayan.feature.monthly_log.table.MonthlyLogTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toMonthlyLog() =
    MonthlyLog(
        id = this[MonthlyLogTable.id],

        month = this[MonthlyLogTable.month],
        year = this[MonthlyLogTable.year],

        goods = this[MonthlyLogTable.goods],
        salary = this[MonthlyLogTable.salary],
        insurance = this[MonthlyLogTable.insurance],
        debt = this[MonthlyLogTable.debt],
        debtPaid = this[MonthlyLogTable.debtPaid],
        other = this[MonthlyLogTable.other],

        finalExpenses =
            this[MonthlyLogTable.finalExpenses],

        finalProfit =
            this[MonthlyLogTable.finalProfit],

        totalSum =
            this[MonthlyLogTable.totalSum],

        createdAt =
            this[MonthlyLogTable.createdAt]
    )