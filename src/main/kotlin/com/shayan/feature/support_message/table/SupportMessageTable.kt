package com.shayan.feature.support_message.table

import com.shayan.feature.support_chat.table.SupportChatTable
import com.shayan.feature.support_message.constants.SupportMessageConst
import com.shayan.feature.users.table.UsersTable
import com.shayan.util.enums.SupportMessageType
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SupportMessageTable : Table(
    SupportMessageConst.TABLE_NAME
) {

    val id =
        varchar(
            SupportMessageConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            SupportMessageConst.USER_ID,
            ANC.ID_LENGTH
        ).nullable()

    val supportChatId =
        varchar(
            SupportMessageConst.SUPPORT_CHAT_ID,
            ANC.ID_LENGTH
        ).references(
            SupportChatTable.id
        )

    val content =
        text(
            SupportMessageConst.CONTENT
        ).nullable()

    val title =
        varchar(
            SupportMessageConst.TITLE,
            SupportMessageConst.TITLE_LENGTH
        ).nullable()

    val messageType =
        enumerationByName<SupportMessageType>(
            SupportMessageConst.MESSAGE_TYPE,
            10,

            )

    val sequence =
        integer(
            SupportMessageConst.SEQUENCE
        )

    override val primaryKey =
        PrimaryKey(id)
}