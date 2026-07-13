package com.shayan.feature.banner.mapper

import com.shayan.feature.banner.model.Banner
import com.shayan.feature.banner.table.BannerTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toBanner() =
    Banner(
        id = this[BannerTable.id],
        title = this[BannerTable.title],
        active = this[BannerTable.active],
        createdAt = this[BannerTable.createdAt]
    )