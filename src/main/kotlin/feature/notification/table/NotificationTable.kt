package com.shayan.feature.notification.table

import com.shayan.feature.notification.constants.NotificationConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object NotificationTable : Table(
    NotificationConst.TABLE_NAME
) {

    val id =
        varchar(
            NotificationConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            NotificationConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id
        )

    val title =
        varchar(
            NotificationConst.TITLE,
            NotificationConst.TITLE_LENGTH
        )

    val date =
        date(
            NotificationConst.DATE
        )

    val content =
        text(
            NotificationConst.CONTENT
        )

    val opened =
        bool(
            NotificationConst.OPENED
        )

    override val primaryKey =
        PrimaryKey(id)
}