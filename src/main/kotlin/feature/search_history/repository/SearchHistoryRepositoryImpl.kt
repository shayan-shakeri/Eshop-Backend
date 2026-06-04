package com.shayan.feature.search_history.repository

import com.shayan.feature.search_history.mapper.toSearchHistory
import com.shayan.feature.search_history.model.SearchHistory
import com.shayan.feature.search_history.table.SearchHistoryTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class SearchHistoryRepositoryImpl: SearchHistoryRepository {
    override suspend fun findHistoryByUserId(userId: String): List<SearchHistory> =
        SearchHistoryTable
            .selectAll()
            .where { SearchHistoryTable.userId eq userId }
            .map { it.toSearchHistory() }


    override suspend fun addHistory(searchHistory: SearchHistory): SearchHistory? {
        SearchHistoryTable.insert {
            it[SearchHistoryTable.id] = searchHistory.id
            it[SearchHistoryTable.userId] = searchHistory.userId
            it[SearchHistoryTable.content] = searchHistory.content
            it[SearchHistoryTable.createdAt] = searchHistory.createdAt
        }
        return SearchHistoryTable
            .selectAll()
            .where { SearchHistoryTable.id eq searchHistory.id }
            .singleOrNull()
            ?.toSearchHistory()
    }

    override suspend fun deleteHistory(userId: String) {
        SearchHistoryTable.deleteWhere { SearchHistoryTable.userId eq userId }
    }

}