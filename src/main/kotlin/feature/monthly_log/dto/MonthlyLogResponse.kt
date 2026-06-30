package com.shayan.feature.monthly_log.dto

import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class MonthlyLogResponse(
    val id: String,

    val month: Int,
    val year: Int,

    val goods: Double,
    val salary: Double,
    val insurance: Double,
    val debt: Double,
    val debtPaid: Boolean,
    val other: Double,

    val finalExpenses: Double,
    val finalProfit: Double,
    val totalSum: Double,

    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)