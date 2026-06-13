package com.shayan.feature.employee.mapper

import com.shayan.feature.employee.model.Employee
import com.shayan.feature.employee.table.EmployeeTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toEmployee(): Employee =
    Employee(
        id = this[EmployeeTable.id],
        roleId = this[EmployeeTable.roleId],
        name = this[EmployeeTable.name],
        nationalId = this[EmployeeTable.nationalId],
        phone = this[EmployeeTable.phone],
        email = this[EmployeeTable.email],
        salary = this[EmployeeTable.salary],
        address = this[EmployeeTable.address],
        gender = this[EmployeeTable.gender],
        birthday = this[EmployeeTable.birthday],
        emergencyContactName = this[EmployeeTable.emergencyContactName],
        emergencyContactPhone = this[EmployeeTable.emergencyContactPhone],
        state = this[EmployeeTable.state],
        passwordHash = this[EmployeeTable.passwordHash],
        iterations = this[EmployeeTable.iterations],
        algorithm = this[EmployeeTable.algorithm],
        salt = this[EmployeeTable.salt]
    )