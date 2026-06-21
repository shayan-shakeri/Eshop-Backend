package com.shayan.feature.product.mapper

import com.shayan.feature.product.model.Product
import com.shayan.feature.product.table.ProductTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toProduct(): Product =
    Product(
        id = this[ProductTable.id],
        categoryId = this[ProductTable.categoryId],
        filterId = this[ProductTable.filterId],
        name = this[ProductTable.name],
        description = this[ProductTable.description],
        originalPrice = this[ProductTable.originalPrice],
        price = this[ProductTable.price],
        stock = this[ProductTable.stock],
        size = this[ProductTable.size],
        length = this[ProductTable.length],
        material = this[ProductTable.material],
        gender = this[ProductTable.gender],
        age = this[ProductTable.age]
    )