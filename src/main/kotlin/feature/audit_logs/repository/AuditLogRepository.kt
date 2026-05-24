package com.shayan.feature.audit_logs.repository

import com.shayan.feature.audit_logs.model.AuditLog

interface AuditLogRepository {
    suspend fun add(auditLog: AuditLog): AuditLog?
    suspend fun findByUserId(id: String): List<AuditLog>
}