package com.shayan.feature.users.mapper

import com.shayan.feature.users.model.Users
import com.shayan.feature.users.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser(): Users = Users(
    id = this[UsersTable.id],
    fullName = this[UsersTable.fullName],
    email = this[UsersTable.email],
    phoneNumber = this[UsersTable.phoneNumber],
    gender = this[UsersTable.gender],
    birthday = this[UsersTable.birthday],
    passwordHash = this[UsersTable.passwordHash],
    iterations = this[UsersTable.iterations],
    algorithm = this[UsersTable.algorithm],
    salt = this[UsersTable.salt]
)
