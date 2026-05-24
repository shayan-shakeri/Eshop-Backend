package com.shayan.feature.audit_logs.repository

import com.shayan.feature.audit_logs.mapper.toAuditLog
import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.table.AuditLogTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class AuditLogRepositoryImpl : AuditLogRepository {
    override suspend fun add(auditLog: AuditLog): AuditLog? {
        AuditLogTable.insert {
            it[AuditLogTable.id] = auditLog.id
            it[AuditLogTable.userId] = auditLog.userId
            it[AuditLogTable.action] = auditLog.action
            it[AuditLogTable.ip] = auditLog.ip
            it[AuditLogTable.createdAt] = auditLog.createdAt
        }

        return AuditLogTable
            .selectAll()
            .where { AuditLogTable.id eq auditLog.id }
            .singleOrNull()
            ?.toAuditLog()
    }


    override suspend fun findByUserId(id: String): List<AuditLog> =
        AuditLogTable
            .selectAll()
            .where { AuditLogTable.id eq id }
            .map { it.toAuditLog() }


}