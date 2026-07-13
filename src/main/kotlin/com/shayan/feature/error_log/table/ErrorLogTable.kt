package com.shayan.feature.error_log.table

import com.shayan.feature.error_log.constants.ErrorLogConst
import core.consts.ANC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object ErrorLogTable : Table(
    ErrorLogConst.TABLE_NAME
) {

    val id = varchar(
        ErrorLogConst.ID,
        ANC.ID_LENGTH
    )

    val errorMessage = text(
        ErrorLogConst.ERROR_MESSAGE
    )

    val createdAt = timestamp(
        ErrorLogConst.CREATED_AT
    )

    override val primaryKey =
        PrimaryKey(id)
}