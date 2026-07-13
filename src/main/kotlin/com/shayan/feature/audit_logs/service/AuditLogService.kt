package com.shayan.feature.audit_logs.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.repository.AuditLogRepository
import core.database.dbQuery
import core.util.IdGenerator
import java.time.Instant

class AuditLogService(
    private val auditLogRepository: AuditLogRepository,
) {

    suspend fun add(userId: String, action: String, ip: String): AuditLog =
        dbQuery {
            val auditLog = AuditLog(
                id = IdGenerator.generate(),
                userId = userId,
                ip = ip,
                action = action,
                createdAt = Instant.now()
            )
            auditLogRepository.add(auditLog) ?: throw FailedToAdd()
        }



    suspend fun get(userId: String): List<AuditLog> =
        dbQuery{ auditLogRepository.findByUserId(userId) }

}