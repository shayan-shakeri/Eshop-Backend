package com.shayan.feature.setting.repository

import com.shayan.feature.setting.model.Setting

interface SettingRepository {

    suspend fun add(
        setting: Setting
    ): Setting?

    suspend fun findById(
        id: String
    ): Setting?

    suspend fun findAll(): List<Setting>

    suspend fun findByProductId(
        productId: String
    ): List<Setting>

    suspend fun update(
        setting: Setting
    ): Setting?

    suspend fun delete(
        id: String
    )
}