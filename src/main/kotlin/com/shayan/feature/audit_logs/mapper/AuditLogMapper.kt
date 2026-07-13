package com.shayan.feature.audit_logs.mapper

import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.table.AuditLogTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toAuditLog(): AuditLog = AuditLog(
    id = this[AuditLogTable.id],
    userId = this[AuditLogTable.userId],
    action = this[AuditLogTable.action],
    ip = this[AuditLogTable.ip],
    createdAt = this[AuditLogTable.createdAt]
)