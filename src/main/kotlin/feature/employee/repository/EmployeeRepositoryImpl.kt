package com.shayan.feature.employee.repository

import com.shayan.feature.employee.mapper.toEmployee
import com.shayan.feature.employee.model.Employee
import com.shayan.feature.employee.table.EmployeeTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import util.EmployeeState

class EmployeeRepositoryImpl : EmployeeRepository {
    override suspend fun getAll(): List<Employee> =
        EmployeeTable
            .selectAll()
            .map { it.toEmployee() }

    override suspend fun findByEmail(email: String): Employee? =
        EmployeeTable
            .selectAll()
            .where { EmployeeTable.email eq email }
            .singleOrNull()
            ?.toEmployee()




    override suspend fun add(employee: Employee): Employee? {
        EmployeeTable.insert {
            it[EmployeeTable.id] = employee.id
            it[EmployeeTable.roleId] = employee.roleId
            it[EmployeeTable.name] = employee.name
            it[EmployeeTable.nationalId] = employee.nationalId
            it[EmployeeTable.phone] = employee.phone
            it[EmployeeTable.email] = employee.email
            it[EmployeeTable.salary] = employee.salary
            it[EmployeeTable.address] = employee.address
            it[EmployeeTable.gender] = employee.gender
            it[EmployeeTable.birthday] = employee.birthday
            it[EmployeeTable.emergencyContactName] = employee.emergencyContactName
            it[EmployeeTable.emergencyContactPhone] = employee.emergencyContactPhone
            it[EmployeeTable.state] = employee.state
            it[EmployeeTable.passwordHash] = employee.passwordHash
            it[EmployeeTable.iterations] = employee.iterations
            it[EmployeeTable.algorithm] = employee.algorithm
            it[EmployeeTable.salt] = employee.salt
        }

        return EmployeeTable
            .selectAll()
            .where { EmployeeTable.id eq employee.id }
            .singleOrNull()
            ?.toEmployee()
    }

    override suspend fun updateInfo(employee: Employee): Employee? {
        EmployeeTable.update({ EmployeeTable.id eq employee.id }) {
            it[EmployeeTable.roleId] = employee.roleId
            it[EmployeeTable.name] = employee.name
            it[EmployeeTable.nationalId] = employee.nationalId
            it[EmployeeTable.phone] = employee.phone
            it[EmployeeTable.email] = employee.email
            it[EmployeeTable.salary] = employee.salary
            it[EmployeeTable.address] = employee.address
            it[EmployeeTable.gender] = employee.gender
            it[EmployeeTable.birthday] = employee.birthday
            it[EmployeeTable.emergencyContactName] = employee.emergencyContactName
            it[EmployeeTable.emergencyContactPhone] = employee.emergencyContactPhone
            it[EmployeeTable.state] = employee.state
        }

        return EmployeeTable
            .selectAll()
            .where { EmployeeTable.id eq employee.id }
            .singleOrNull()
            ?.toEmployee()
    }

    override suspend fun updatePassword(employee: Employee): Employee? {
        EmployeeTable.update({ EmployeeTable.id eq employee.id }) {
            it[EmployeeTable.passwordHash] = employee.passwordHash
            it[EmployeeTable.iterations] = employee.iterations
            it[EmployeeTable.algorithm] = employee.algorithm
            it[EmployeeTable.salt] = employee.salt
        }
        return EmployeeTable
            .selectAll()
            .where { EmployeeTable.id eq employee.id }
            .singleOrNull()
            ?.toEmployee()
    }

    override suspend fun delete(id: String) {
        EmployeeTable.update({ EmployeeTable.id eq id }) {
            it[EmployeeTable.state] = EmployeeState.Terminated
        }
    }

    override suspend fun checkIfTerminated(id: String): Boolean {
        val result = EmployeeTable
            .selectAll()
            .where { EmployeeTable.id eq id }
            .singleOrNull()
            ?.toEmployee()

        return result != null && result.state != EmployeeState.Terminated
    }
}