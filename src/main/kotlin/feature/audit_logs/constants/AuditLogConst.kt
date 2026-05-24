package com.shayan.feature.audit_logs.constants

object AuditLogConst {
    const val TABLE_NAME = "audit_logs"
    const val ID = "id"
    const val USER_ID = "type"
    const val ACTION = "author"
    const val IP = "author"
    const val CREATED_AT = "author"

    const val IP_LENGTH = 45

    const val MAIN_ROUTE = "/audit"
    const val GET_ROUTE = "/{id}"
    const val GET_PARAM = "id"


}