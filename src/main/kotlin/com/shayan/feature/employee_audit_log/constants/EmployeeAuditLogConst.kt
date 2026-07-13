package com.shayan.feature.employee_audit_log.constants

object EmployeeAuditLogConst {
    const val TABLE_NAME = "employee_audit_log"
    const val ID = "id"
    const val EMPLOYEE_ID = "employee_id"
    const val CODE = "code"
    const val ACTION = "action"
    const val IP = "ip"
    const val CREATED_AT = "created_at"

    //------------------numeral
    const val IP_LENGTH = 45

    //------------------route
    private const val EMPLOYEE_ID_URL = "{employeeId}"
    const val MAIN_ROUTE = "employee-audit-log"
    const val EMPLOYEE_ID_PARAM = "employeeId"

    const val READ_ROUTE = "/read/$EMPLOYEE_ID_URL"

}