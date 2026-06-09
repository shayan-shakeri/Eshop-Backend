package com.shayan.feature.user_pic.repository

import com.shayan.feature.user_pic.model.UserPic

interface UserPicRepository {
    suspend fun getUserPic(userId: String): UserPic?

    suspend fun addUserPic(userPic: UserPic): UserPic?

    suspend fun updateUserPic(userPic: UserPic): UserPic?

    suspend fun deleteUserPic(userId: String)
}