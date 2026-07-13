package com.shayan.feature.filter.repository

import com.shayan.feature.filter.model.Filter

interface RepositoryFilter {
    suspend fun addFilter(filter: Filter): Filter?

    suspend fun readFilter(): List<Filter>
}