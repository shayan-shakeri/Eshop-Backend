package com.shayan.feature.employee.model

import com.shayan.util.Gender
import util.EmployeeState
import java.math.BigDecimal
import java.time.LocalDate

data class Employee(
    val id: String ,
    val roleId: String,
    val name: String,
    val nationalId: String,
    val phone: String,
    val email: String,
    val salary: BigDecimal,
    val address: String,
    val gender: Gender,
    val birthday: LocalDate,
    val emergencyContactName: String,
    val emergencyContactPhone: String,
    val state: EmployeeState,
    val passwordHash: String,
    val iterations: Int,
    val algorithm: String,
    val salt: String
)

