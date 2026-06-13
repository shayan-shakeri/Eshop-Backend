package com.shayan.feature.employee.table

import com.shayan.feature.role.table.RoleTable
import com.shayan.util.Gender
import core.consts.ANC
import feature.employee.constants.EmployeeConst
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import util.EmployeeState

object EmployeeTable : Table(EmployeeConst.TABLE_NAME) {
    val id = varchar(EmployeeConst.ID, ANC.ID_LENGTH)
    val roleId = varchar(EmployeeConst.ROLE_ID, ANC.ID_LENGTH).references(
        RoleTable.id,
        onDelete = ReferenceOption.RESTRICT,
        onUpdate = ReferenceOption.RESTRICT
    )

    val name = varchar(EmployeeConst.NAME, EmployeeConst.NAME_LENGTH)
    val nationalId = varchar(EmployeeConst.NATIONAL_ID, EmployeeConst.NATIONAL_ID_LENGTH).uniqueIndex()
    val phone = varchar(EmployeeConst.PHONE, EmployeeConst.PHONE_LENGTH).uniqueIndex()
    val email = varchar(EmployeeConst.EMAIL, EmployeeConst.EMAIL_LENGTH).uniqueIndex()
    val salary = decimal(EmployeeConst.SALARY, EmployeeConst.SALARY_PRECISION, EmployeeConst.SALARY_SCALE)
    val address = text(EmployeeConst.ADDRESS)
    val gender = enumeration<Gender>(EmployeeConst.GENDER)
    val birthday = date(EmployeeConst.BIRTHDAY)
    val emergencyContactName =
        varchar(EmployeeConst.EMERGENCY_CONTACT_NAME, EmployeeConst.EMERGENCY_CONTACT_NAME_LENGTH)
    val emergencyContactPhone =
        varchar(EmployeeConst.EMERGENCY_CONTACT_PHONE, EmployeeConst.EMERGENCY_CONTACT_PHONE_LENGTH)
    val state = enumeration<EmployeeState>(EmployeeConst.STATE)
    val passwordHash = varchar(EmployeeConst.PASSWORD_HASH, EmployeeConst.PASSWORD_HASH_LENGTH)
    val iterations = integer(EmployeeConst.ITERATIONS)
    val algorithm = varchar(EmployeeConst.ALGORITHM, EmployeeConst.ALGORITHM_LENGTH)
    val salt = varchar(EmployeeConst.SALT, EmployeeConst.SALT_LENGTH)

    override val primaryKey = PrimaryKey(id)

}
