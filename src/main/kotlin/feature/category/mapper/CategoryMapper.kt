package com.shayan.feature.category.mapper

import com.shayan.feature.category.model.Category
import com.shayan.feature.category.table.CategoryTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toCategory(): Category =
    Category(
        id = this[CategoryTable.id],
        name = this[CategoryTable.name]
    )