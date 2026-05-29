package com.shayan.feature.audit_logs.table

import com.shayan.feature.audit_logs.constants.AuditLogConst
import com.shayan.feature.users.table.UsersTable
import core.consts.UNC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object AuditLogTable : Table(AuditLogConst.TABLE_NAME) {
    val id = varchar(AuditLogConst.ID, UNC.ID_LENGTH)
    val userId = varchar(AuditLogConst.USER_ID, UNC.ID_LENGTH)
    val action = text(AuditLogConst.ACTION)
    val ip = varchar(AuditLogConst.IP, AuditLogConst.IP_LENGTH)
    val createdAt = timestamp(AuditLogConst.CREATED_AT)

}