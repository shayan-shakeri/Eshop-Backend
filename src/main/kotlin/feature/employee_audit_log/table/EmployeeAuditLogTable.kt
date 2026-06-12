package com.shayan.feature.employee_audit_log.table

import com.shayan.feature.employee_audit_log.constants.EmployeeAuditLogConst
import core.consts.ANC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object EmployeeAuditLogTable: Table(EmployeeAuditLogConst.TABLE_NAME) {
    val id = varchar(EmployeeAuditLogConst.ID, ANC.ID_LENGTH)
    val employee_id = varchar(EmployeeAuditLogConst.EMPLOYEE_ID, ANC.ID_LENGTH)
    val role_id = varchar(EmployeeAuditLogConst.ROLE_ID, ANC.ID_LENGTH)
    val action = text(EmployeeAuditLogConst.ACTION)
    val ip = varchar(EmployeeAuditLogConst.IP, EmployeeAuditLogConst.IP_LENGTH)
    val created_at = timestamp(EmployeeAuditLogConst.CREATED_AT)

    override val primaryKey = PrimaryKey(id)
}