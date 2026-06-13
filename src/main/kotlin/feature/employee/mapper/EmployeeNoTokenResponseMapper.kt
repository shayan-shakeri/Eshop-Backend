package com.shayan.feature.employee.mapper

import com.shayan.feature.employee.dto.EmployeeNoTokenResponse
import com.shayan.feature.employee.dto.EmployeeResponse
import com.shayan.feature.employee.model.Employee

fun Employee.toEmployeeNoTokenResponse(): EmployeeNoTokenResponse =
    EmployeeNoTokenResponse(
        id = id,
        roleId = roleId,
        name = name,
        nationalId = nationalId,
        phone = phone,
        email = email,
        salary = salary,
        address = address,
        gender = gender,
        birthday = birthday,
        emergencyContactName = emergencyContactName,
        emergencyContactPhone = emergencyContactPhone,
        state = state
    )