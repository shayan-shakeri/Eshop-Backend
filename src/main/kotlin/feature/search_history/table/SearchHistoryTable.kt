package com.shayan.feature.search_history.table

import com.shayan.feature.search_history.constants.SearchHistoryConstant
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object SearchHistoryTable: Table(SearchHistoryConstant.TABLE_NAME) {
    val id = varchar(SearchHistoryConstant.ID, ANC.ID_LENGTH)
    val userId = varchar(SearchHistoryConstant.USER_ID, ANC.ID_LENGTH).references(
        UsersTable.id,
        onUpdate = ReferenceOption.RESTRICT,
        onDelete = ReferenceOption.CASCADE
    )
    val content = varchar(SearchHistoryConstant.CONTENT, SearchHistoryConstant.CONTENT_LENGTH)
    val createdAt = timestamp(SearchHistoryConstant.CREATED_AT)

    override val primaryKey = PrimaryKey(id)
}