package com.shayan.feature.users.repository

import com.shayan.feature.users.mapper.toUser
import com.shayan.feature.users.model.Users
import com.shayan.feature.users.table.UsersTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserRepositoryImpl : UserRepository {
    override suspend fun findById(id: String): Users? =
        UsersTable
            .selectAll()
            .where { UsersTable.id eq id }
            .singleOrNull()
            ?.toUser()

    override suspend fun findByEmail(email: String): Users? =
        UsersTable
            .selectAll()
            .where { UsersTable.email eq email }
            .singleOrNull()
            ?.toUser()

    override suspend fun add(users: Users): Users? {
        UsersTable.insert {
            it[UsersTable.id] = users.id
            it[UsersTable.fullName] = users.fullName
            it[UsersTable.email] = users.email
            it[UsersTable.phoneNumber] = users.phoneNumber
            it[UsersTable.gender] = users.gender
            it[UsersTable.birthday] = users.birthday
            it[UsersTable.passwordHash] = users.passwordHash
            it[UsersTable.iterations] = users.iterations
            it[UsersTable.algorithm] = users.algorithm
            it[UsersTable.salt] = users.salt
        }

        return UsersTable
            .selectAll()
            .where { UsersTable.id eq users.id }
            .singleOrNull()
            ?.toUser()
    }

    override suspend fun updateInfo(users: Users): Users? {
        UsersTable.update({ UsersTable.id eq users.id }) {
            it[UsersTable.fullName] = users.fullName
            it[UsersTable.email] = users.email
            it[UsersTable.phoneNumber] = users.phoneNumber
            it[UsersTable.gender] = users.gender
            it[UsersTable.birthday] = users.birthday
        }

        return UsersTable
            .selectAll()
            .where { UsersTable.id eq users.id }
            .singleOrNull()
            ?.toUser()
    }

    override suspend fun updatePassword(users: Users): Users? {
        UsersTable.update({ UsersTable.id eq users.id }) {
            it[UsersTable.passwordHash] = users.passwordHash
            it[UsersTable.iterations] = users.iterations
            it[UsersTable.algorithm] = users.algorithm
            it[UsersTable.salt] = users.salt
        }

        return UsersTable
            .selectAll()
            .where { UsersTable.id eq users.id }
            .singleOrNull()
            ?.toUser()
    }

    override suspend fun delete(id: String) {
        UsersTable.deleteWhere { UsersTable.id eq id }
    }
}