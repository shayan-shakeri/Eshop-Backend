package com.shayan.feature.setting.table

import com.shayan.feature.setting.constants.SettingConst
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object SettingTable : Table(SettingConst.TABLE_NAME) {

    val id = varchar(
        SettingConst.ID,
        ANC.ID_LENGTH
    )

    val productId = varchar(
        SettingConst.PRODUCT_ID,
        ANC.ID_LENGTH
    )

    val name = varchar(
        SettingConst.NAME,
        SettingConst.NAME_LENGTH
    )

    val value = varchar(
        SettingConst.VALUE,
        SettingConst.VALUE_LENGTH
    )

    override val primaryKey =
        PrimaryKey(id)
}