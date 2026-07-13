package com.shayan.feature.setting.mapper

import com.shayan.feature.setting.model.Setting
import com.shayan.feature.setting.table.SettingTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSetting(): Setting =
    Setting(
        id = this[SettingTable.id],
        productId = this[SettingTable.productId],
        name = this[SettingTable.name],
        value = this[SettingTable.value]
    )