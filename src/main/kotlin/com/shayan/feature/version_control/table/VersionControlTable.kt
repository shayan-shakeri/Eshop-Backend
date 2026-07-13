package com.shayan.feature.version_control.table

import com.shayan.feature.version_control.constants.VersionControlConst
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object VersionControlTable : Table(
    VersionControlConst.TABLE_NAME
) {

    val id = varchar(
        VersionControlConst.ID,
        40
    )

    val version = varchar(
        VersionControlConst.VERSION,
        VersionControlConst.VERSION_LENGTH
    )

    val active = bool(
        VersionControlConst.ACTIVE
    )

    val description = text(
        VersionControlConst.DESCRIPTION
    )

    val forced = bool(
        VersionControlConst.FORCED
    )

    val createdAt = timestamp(
        VersionControlConst.CREATED_AT
    )

    override val primaryKey =
        PrimaryKey(id)
}