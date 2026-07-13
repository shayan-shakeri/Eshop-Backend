package com.shayan.feature.monthly_log.model

import java.math.BigDecimal
import java.time.Instant

data class MonthlyLog(
    val id: String,

    val month: Int,
    val year: Int,

    val goods: BigDecimal,
    val salary: BigDecimal,
    val insurance: BigDecimal,
    val debt: BigDecimal,
    val debtPaid: Boolean,
    val other: BigDecimal,

    val finalExpenses: BigDecimal,
    val finalProfit: BigDecimal,
    val totalSum: BigDecimal,

    val createdAt: Instant
)