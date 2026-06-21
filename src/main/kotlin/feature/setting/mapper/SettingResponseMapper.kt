package com.shayan.feature.setting.mapper

import com.shayan.feature.setting.dto.SettingResponse
import com.shayan.feature.setting.model.Setting

fun Setting.toSettingResponse(): SettingResponse =
    SettingResponse(
        id = this.id,
        productId = this.productId,
        name = this.name,
        value = this.value
    )