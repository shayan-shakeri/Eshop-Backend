package com.shayan.feature.filter.mapper

import com.shayan.feature.filter.model.Filter
import com.shayan.feature.filter.table.FilterTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toFilter(): Filter =
    Filter(
        id = this[FilterTable.id],
        categoryId = this[FilterTable.categoryId],
        name = this[FilterTable.name],
        imageTitle = this[FilterTable.categoryId]
    )