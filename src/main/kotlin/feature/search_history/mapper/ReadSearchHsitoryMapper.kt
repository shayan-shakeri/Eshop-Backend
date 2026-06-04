package com.shayan.feature.search_history.mapper

import com.shayan.feature.search_history.dto.ReadSearchHistory
import com.shayan.feature.search_history.model.SearchHistory

fun SearchHistory.toReadSearchHistory(): ReadSearchHistory = ReadSearchHistory(
    id = this.id,
    userId = this.userId,
    content = this.content,
    createdAt = this.createdAt,
)