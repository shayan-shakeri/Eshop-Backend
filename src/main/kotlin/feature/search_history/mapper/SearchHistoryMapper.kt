package com.shayan.feature.search_history.mapper

import com.shayan.feature.search_history.model.SearchHistory
import com.shayan.feature.search_history.table.SearchHistoryTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSearchHistory(): SearchHistory =
    SearchHistory(
        id = this[SearchHistoryTable.id],
        userId = this[SearchHistoryTable.userId],
        content = this[SearchHistoryTable.content],
        createdAt = this[SearchHistoryTable.createdAt]
    )