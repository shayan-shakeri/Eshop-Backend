package com.shayan.feature.role.repository

import com.shayan.feature.role.model.Role

interface RoleRepository {
    suspend fun add(role: Role): Role?

    suspend fun finRoleById(id: String): Role?

    suspend fun getAll(): List<Role>

}