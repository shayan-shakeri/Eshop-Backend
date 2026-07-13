package com.shayan.feature.user_pic.table

import com.shayan.feature.user_pic.constants.UserPicConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UserPicTable: Table(UserPicConst.TABLE_NAME) {
    val id = varchar(UserPicConst.ID, ANC.ID_LENGTH)
    val userId = varchar(UserPicConst.USER_ID, ANC.ID_LENGTH).references(
        UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.RESTRICT
    )
    val title = varchar(UserPicConst.TITLE, UserPicConst.TITLE_LENGTH)
}