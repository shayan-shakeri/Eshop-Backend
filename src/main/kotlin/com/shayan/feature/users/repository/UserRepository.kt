package com.shayan.feature.users.repository

import com.shayan.feature.users.model.Users

interface UserRepository {
    suspend fun findById(id: String): Users?
    suspend fun findByEmail(email: String): Users?

    suspend fun add(users: Users): Users?

    suspend fun updateInfo(users: Users): Users?
    suspend fun updatePassword(users: Users): Users?

    suspend fun delete(id: String)
}