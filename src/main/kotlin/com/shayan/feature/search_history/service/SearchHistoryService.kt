package com.shayan.feature.search_history.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.search_history.constants.SearchHistoryConstant
import com.shayan.feature.search_history.dto.ReadSearchHistory
import com.shayan.feature.search_history.mapper.toReadSearchHistory
import com.shayan.feature.search_history.model.SearchHistory
import com.shayan.feature.search_history.repository.SearchHistoryRepository
import core.database.dbQuery
import core.util.IdGenerator
import java.time.Instant

class SearchHistoryService(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val auditLogService: AuditLogService
) {

    suspend fun addSearchHistory(userId: String, content: String): ReadSearchHistory =
        dbQuery {
            val id = IdGenerator.generate()
            val now = Instant.now()

            val history = SearchHistory(
                id = id,
                userId = userId,
                content = content,
                createdAt = now
            )
            searchHistoryRepository.addHistory(history)?.toReadSearchHistory() ?: throw FailedToAdd()
        }

    suspend fun readHistory(userId: String): List<ReadSearchHistory> = dbQuery {
        searchHistoryRepository.findHistoryByUserId(userId).map { it.toReadSearchHistory() }
    }

    suspend fun deleteSearchHistory(userId: String, ip: String) {
        runCatching { auditLogService.add(userId, SearchHistoryConstant.DELETE_ACTION, ip) }
        dbQuery {
            searchHistoryRepository.deleteHistory(userId)
        }
    }
}
