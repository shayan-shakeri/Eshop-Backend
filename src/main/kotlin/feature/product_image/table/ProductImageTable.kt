package com.shayan.feature.product_image.table

import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object ProductImageTable : Table(`SettingConst.kt`.TABLE_NAME) {

    val id = varchar(
        `SettingConst.kt`.ID,
        ANC.ID_LENGTH
    )

    val productId = varchar(
        `SettingConst.kt`.PRODUCT_ID,
        ANC.ID_LENGTH
    )

    val previewImage = bool(
        `SettingConst.kt`.PREVIEW_IMAGE
    )

    val title = varchar(
        `SettingConst.kt`.TITLE,
        `SettingConst.kt`.TITLE_LENGTH
    )

    override val primaryKey = PrimaryKey(id)
}