package com.shayan.feature.monthly_log.table

import com.shayan.feature.monthly_log.constants.MonthlyLogConst
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp


object MonthlyLogTable : Table(
    MonthlyLogConst.TABLE_NAME
) {

    val id = varchar(
        MonthlyLogConst.ID,
        40
    )

    val month = integer(
        MonthlyLogConst.MONTH
    )

    val year = integer(
        MonthlyLogConst.YEAR
    )

    val goods = decimal(
        MonthlyLogConst.GOODS,
        10,
        2
    )

    val salary = decimal(
        MonthlyLogConst.SALARY,
        10,
        2
    )

    val insurance = decimal(
        MonthlyLogConst.INSURANCE,
        10,
        2
    )

    val debt = decimal(
        MonthlyLogConst.DEBT,
        10,
        2
    )

    val debtPaid = bool(
        MonthlyLogConst.DEBT_PAID
    )

    val other = decimal(
        MonthlyLogConst.OTHER,
        10,
        2
    )

    val finalExpenses = decimal(
        MonthlyLogConst.FINAL_EXPENSES,
        10,
        2
    )

    val finalProfit = decimal(
        MonthlyLogConst.FINAL_PROFIT,
        10,
        2
    )

    val totalSum = decimal(
        MonthlyLogConst.TOTAL_SUM,
        10,
        2
    )

    val createdAt = timestamp(
        MonthlyLogConst.CREATED_AT
    )

    override val primaryKey =
        PrimaryKey(id)
}