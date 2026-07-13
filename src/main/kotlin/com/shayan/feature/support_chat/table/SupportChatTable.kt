package com.shayan.feature.support_chat.table

import com.shayan.feature.support_chat.constants.SupportChatConst
import com.shayan.feature.users.table.UsersTable
import com.shayan.util.enums.SupportChatPriority
import com.shayan.util.enums.SupportChatStatus
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object SupportChatTable : Table(
    SupportChatConst.TABLE_NAME
) {

    val id =
        varchar(
            SupportChatConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            SupportChatConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id
        )

    val name =
        varchar(
            SupportChatConst.NAME,
            SupportChatConst.NAME_LENGTH
        )

    val priority =
        enumerationByName<SupportChatPriority>(
            SupportChatConst.PRIORITY,
            10
        )

    val status =
        enumerationByName<SupportChatStatus>(
            SupportChatConst.STATUS,
            20
        )

    override val primaryKey =
        PrimaryKey(id)
}