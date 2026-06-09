package com.shayan.feature.user_pic.mapper

import com.shayan.feature.user_pic.model.UserPic
import com.shayan.feature.user_pic.table.UserPicTable
import com.shayan.feature.users.table.UsersTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUserPic(): UserPic =
    UserPic(
        id = this[UserPicTable.id],
        userId = this[UserPicTable.userId],
        title = this[UserPicTable.title]
    )