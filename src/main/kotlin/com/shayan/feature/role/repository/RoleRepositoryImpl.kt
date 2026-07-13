package com.shayan.feature.role.repository

import com.shayan.feature.role.mapper.toRole
import com.shayan.feature.role.model.Role
import com.shayan.feature.role.table.RoleTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class RoleRepositoryImpl: RoleRepository {
    override suspend fun add(role: Role): Role? {
        RoleTable.insert{
            it[RoleTable.id] = role.id
            it[RoleTable.title] = role.title
            it[RoleTable.desc] = role.description
            it[RoleTable.code] = role.code
        }
        return RoleTable
            .selectAll()
            .where { RoleTable.id eq role.id }
            .singleOrNull()
            ?.toRole()
    }

    override suspend fun finRoleById(id: String): Role? =
        RoleTable
            .selectAll()
            .where { RoleTable.id eq id }
            .singleOrNull()
            ?.toRole()

    override suspend fun getAll(): List<Role> =
        RoleTable
            .selectAll()
            .map { it.toRole() }
}