package com.shayan.feature.monthly_log.constants

object MonthlyLogConst {

    const val TABLE_NAME = "monthly_log"

    const val ID = "id"

    const val MONTH = "month"
    const val YEAR = "year"

    const val GOODS = "goods"
    const val SALARY = "salary"
    const val INSURANCE = "insurance"
    const val DEBT = "debt"
    const val DEBT_PAID = "debt_paid"
    const val OTHER = "other"

    const val FINAL_EXPENSES = "final_expenses"
    const val FINAL_PROFIT = "final_profit"
    const val TOTAL_SUM = "total_sum"

    const val CREATED_AT = "created_at"

    // routes
    const val MAIN_ROUTE = "/monthly-log"

    const val READ_ROUTE = "/read"
    const val READ_SINGLE_ROUTE = "/read/{id}"
    const val READ_CURRENT_ROUTE = "/current"
}