package com.shayan.feature.category.repository

import com.shayan.feature.category.mapper.toCategory
import com.shayan.feature.category.model.Category
import com.shayan.feature.category.table.CategoryTable
import com.shayan.feature.employee.table.EmployeeTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun getAllCategories(): List<Category> =
        CategoryTable
            .selectAll()
            .map { it.toCategory() }

    override suspend fun addCategory(category: Category): Category? {
        CategoryTable.insert {
            it[CategoryTable.id] = category.id
            it[CategoryTable.name] = category.name
        }
        return CategoryTable
            .selectAll()
            .where { CategoryTable.id eq category.id }
            .singleOrNull()
            ?.toCategory()
    }
}