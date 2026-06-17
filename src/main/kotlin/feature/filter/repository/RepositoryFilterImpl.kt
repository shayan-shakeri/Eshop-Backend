package com.shayan.feature.filter.repository

import com.shayan.feature.filter.mapper.toFilter
import com.shayan.feature.filter.mapper.toFilterResponse
import com.shayan.feature.filter.model.Filter
import com.shayan.feature.filter.table.FilterTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class RepositoryFilterImpl: RepositoryFilter {
    override suspend fun addFilter(filter: Filter): Filter? {
        FilterTable.insert {
            it[FilterTable.id] = filter.id
            it[FilterTable.name] = filter.name
            it[FilterTable.categoryId] = filter.categoryId
            it[FilterTable.imageTitle] = filter.imageTitle
        }

        return FilterTable
            .selectAll()
            .where { FilterTable.id eq filter.id }
            .singleOrNull()
            ?.toFilter()
    }

    override suspend fun readFilter(): List<Filter> =
        FilterTable.selectAll().map { it.toFilter() }

}