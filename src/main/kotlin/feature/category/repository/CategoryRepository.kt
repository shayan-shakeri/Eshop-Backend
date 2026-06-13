package com.shayan.feature.category.repository

import com.shayan.feature.category.model.Category

interface CategoryRepository {
    suspend fun getAllCategories(): List<Category>

    suspend fun addCategory(category: Category): Category?
}