package com.shayan.feature.search_history.repository

import com.shayan.feature.search_history.model.SearchHistory

interface SearchHistoryRepository {
    suspend fun findHistoryByUserId(userId: String): List<SearchHistory>
    suspend fun addHistory(searchHistory: SearchHistory): SearchHistory?
    suspend fun deleteHistory(userId: String)
}