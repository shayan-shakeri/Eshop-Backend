package com.shayan.feature.employee.repository

import com.shayan.feature.employee.model.Employee
import org.jetbrains.exposed.sql.ResultRow

interface EmployeeRepository {
    suspend fun getAll(): List<Employee>
    suspend fun findByEmail(email: String): Employee?

    suspend fun add(employee: Employee): Employee?

    suspend fun updateInfo(employee: Employee): Employee?
    suspend fun updatePassword(employee: Employee): Employee?

    suspend fun delete(id: String)
    suspend fun checkIfTerminated(id: String): Boolean


}