package com.shayan.feature.audit_logs.constants

object AuditLogConst {
    const val TABLE_NAME = "audit_logs"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val ACTION = "action"
    const val IP = "ip"
    const val CREATED_AT = "created_at"

    //-----------------Numeral
    const val IP_LENGTH = 45

    //-----------------Routes
    const val MAIN_ROUTE = "/audit"
    const val GET_ROUTE = "/{id}"
    const val GET_PARAM = "id"


}