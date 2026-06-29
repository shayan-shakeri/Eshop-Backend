package com.shayan.feature.banner.table

import com.shayan.feature.banner.constants.BannerConst
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.timestamp

object BannerTable : Table(
    BannerConst.TABLE_NAME
) {

    val id = varchar(
        BannerConst.ID,
        40
    )

    val title = varchar(
        BannerConst.TITLE,
        BannerConst.TITLE_LENGTH
    )

    val active = bool(
        BannerConst.ACTIVE
    )

    val createdAt = timestamp(
        BannerConst.CREATED_AT
    )

    override val primaryKey =
        PrimaryKey(id)
}