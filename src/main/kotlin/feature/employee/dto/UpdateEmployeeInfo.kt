package com.shayan.feature.employee.dto

import com.shayan.util.enum.Gender
import com.shayan.util.serializer.LocalDateSerializer
import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import java.math.BigDecimal
import java.time.LocalDate

@Serializable
data class UpdateEmployeeInfo(
    val id: String,
    val name: String,
    val nationalId: String,
    val phone: String,
    val email: String,
    @Serializable(with = BigDecimalSerializer::class)
    val salary: BigDecimal,
    val address: String,
    val gender: Gender,
    @Serializable(with = LocalDateSerializer::class)
    val birthday: LocalDate,
    val emergencyContactName: String,
    val emergencyContactPhone: String,
    val ip: String
)
