package com.shayan.feature.user_pic.repository

import com.shayan.feature.user_pic.mapper.toUserPic
import com.shayan.feature.user_pic.model.UserPic
import com.shayan.feature.user_pic.table.UserPicTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class UserPicRepositoryImpl: UserPicRepository {
    override suspend fun getUserPic(userId: String): UserPic? =
        UserPicTable
            .selectAll()
            .where { UserPicTable.userId eq userId }
            .singleOrNull()
            ?.toUserPic()

    override suspend fun addUserPic(userPic: UserPic): UserPic? {
        UserPicTable.insert {
            it[UserPicTable.id] = userPic.id
            it[UserPicTable.userId] = userPic.userId
            it[UserPicTable.title] = userPic.title
        }
        return UserPicTable
            .selectAll()
            .where { UserPicTable.userId eq userPic.userId }
            .singleOrNull()
            ?.toUserPic()
    }

    override suspend fun updateUserPic(userPic: UserPic): UserPic? {
        UserPicTable.update({ UserPicTable.userId eq userPic.userId }) {
            it[UserPicTable.title] = userPic.title
        }

        return UserPicTable
            .selectAll()
            .where { UserPicTable.userId eq userPic.userId }
            .singleOrNull()
            ?.toUserPic()
    }

    override suspend fun deleteUserPic(userId: String) {
        UserPicTable.deleteWhere { UserPicTable.userId eq userId }
    }
}