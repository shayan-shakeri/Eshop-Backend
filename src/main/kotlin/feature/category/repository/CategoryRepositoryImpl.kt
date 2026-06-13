package com.shayan.feature.category.repository

import com.shayan.feature.category.mapper.toCategory
import com.shayan.feature.category.model.Category
import com.shayan.feature.employee.table.EmployeeTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> =
        EmployeeTable
            .selectAll()
            .map { it.toCategory() }

    override suspend fun addCategory(category: Category): Category? {
        EmployeeTable.insert {
            it[EmployeeTable.id] = category.id
            it[EmployeeTable.name] = category.name
        }
        return EmployeeTable
            .selectAll()
            .where { EmployeeTable.id eq category.id }
            .singleOrNull()
            ?.toCategory()
    }
}