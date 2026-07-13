package feature.employee.constants

object EmployeeConst {
    const val TABLE_NAME = "employee"
    const val ID = "id"
    const val ROLE_ID = "role_id"
    const val NAME = "name"
    const val NATIONAL_ID = "national_id"
    const val PHONE = "phone"
    const val EMAIL = "email"
    const val SALARY = "salary"
    const val ADDRESS = "address"
    const val GENDER = "gender"
    const val BIRTHDAY = "birthday"
    const val EMERGENCY_CONTACT_NAME = "emergency_contact_name"
    const val EMERGENCY_CONTACT_PHONE = "emergency_contact_phone"
    const val STATE = "state"
    const val PASSWORD_HASH = "password_hash"
    const val ITERATIONS = "iterations"
    const val ALGORITHM = "algorithm"
    const val SALT = "salt"

    //-------------NUMERALS
    const val NAME_LENGTH = 100
    const val NATIONAL_ID_LENGTH = 20
    const val PHONE_LENGTH = 16
    const val EMAIL_LENGTH = 255
    const val EMERGENCY_CONTACT_NAME_LENGTH = 20
    const val EMERGENCY_CONTACT_PHONE_LENGTH = 15
    const val PASSWORD_HASH_LENGTH = 128
    const val ALGORITHM_LENGTH = 64
    const val SALT_LENGTH = 64
    const val SALARY_PRECISION = 64
    const val SALARY_SCALE = 64

    //-------------ROUTING
    private const val IP_URL = "{ip}"
    const val IP_PARAM = "ip"
    const val MAIN_ROUTE = "/employee"

    const val CREATE_ROUTE = "/create"
    const val LOGIN_ROUTE = "/login"

    private const val UPDATE_ROUTE = "/update"
    const val UPDATE_INFO_ROUTE = "$UPDATE_ROUTE/info"
    const val UPDATE_PASSWORD_ROUTE = "$UPDATE_ROUTE/password"

    const val DELETE_ROUTE = "/delete"
    const val GET_ALL_ROUTE = "/all/$IP_URL"

    //---------------ACTIONS
    const val CREATE_ACTION = "Employee account was created"
    const val LOGIN_ACTION = "Employee logged into account"
    const val GET_ALL_ACTION = "Employee loaded all the users"
    const val UPDATE_INFO_ACTION = "Employee information was updated"
    const val UPDATE_PASSWORD_ACTION = "Employee password was updated"
    const val DELETE_ACTION = "Employee account was deleted"

}
