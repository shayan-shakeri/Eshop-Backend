package com.shayan.feature.users.table

import com.shayan.feature.users.constants.UsersConst
import com.shayan.util.Gender
import core.consts.UNC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object UsersTable : Table(UsersConst.TABLE_NAME) {
    val id = varchar(UsersConst.ID, UNC.ID_LENGTH)
    val fullName = varchar(UsersConst.FULL_NAME, UsersConst.FULL_NAME_LENGTH)
    val email = varchar(UsersConst.EMAIL, UsersConst.EMAIL_LENGTH)
    val phoneNumber = varchar(UsersConst.PHONE_NUMBER, UsersConst.PHONE_LENGTH)
    val gender = enumerationByName<Gender>(
        UsersConst.GENDER,
        20
    )
    val birthday = date(UsersConst.BIRTHDAY)
    val passwordHash = varchar(UsersConst.PASSWORD_HASH, UsersConst.PASSWORD_HASH_LENGTH)
    val iterations = integer(UsersConst.ITERATIONS)
    val algorithm = varchar(UsersConst.ALGORITHM, UsersConst.ALGORITHM_LENGTH)
    val salt = varchar(UsersConst.SALT, UsersConst.SALT_LENGTH)

    override val primaryKey = PrimaryKey(id)
}