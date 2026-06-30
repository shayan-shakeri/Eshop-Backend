package com.shayan.feature.monthly_log.mapper

import com.shayan.feature.monthly_log.dto.MonthlyLogResponse
import com.shayan.feature.monthly_log.model.MonthlyLog

fun MonthlyLog.toMonthlyLogResponse() =
    MonthlyLogResponse(
        id = id,

        month = month,
        year = year,

        goods = goods.toDouble(),
        salary = salary.toDouble(),
        insurance = insurance.toDouble(),
        debt = debt.toDouble(),
        debtPaid = debtPaid,
        other = other.toDouble(),

        finalExpenses =
            finalExpenses.toDouble(),

        finalProfit =
            finalProfit.toDouble(),

        totalSum =
            totalSum.toDouble(),

        createdAt =
            createdAt
    )