package com.shayan.feature.setting.repository

import com.shayan.feature.setting.mapper.toSetting
import com.shayan.feature.setting.model.Setting
import com.shayan.feature.setting.table.SettingTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class SettingRepositoryImpl : SettingRepository {

    override suspend fun add(
        setting: Setting
    ): Setting? {

        SettingTable.insert {

            it[id] = setting.id
            it[productId] = setting.productId
            it[name] = setting.name
            it[value] = setting.value
        }

        return findById(setting.id)
    }

    override suspend fun findById(
        id: String
    ): Setting? =
        SettingTable
            .selectAll()
            .where {
                SettingTable.id eq id
            }
            .singleOrNull()
            ?.toSetting()

    override suspend fun findAll(): List<Setting> =
        SettingTable
            .selectAll()
            .map {
                it.toSetting()
            }

    override suspend fun findByProductId(
        productId: String
    ): List<Setting> =
        SettingTable
            .selectAll()
            .where {
                SettingTable.productId eq productId
            }
            .map {
                it.toSetting()
            }

    override suspend fun update(
        setting: Setting
    ): Setting? {

        SettingTable.update(
            where = {
                SettingTable.id eq setting.id
            }
        ) {

            it[name] = setting.name
            it[value] = setting.value
        }

        return findById(setting.id)
    }

    override suspend fun delete(
        id: String
    ) {

        SettingTable.deleteWhere {
            SettingTable.id eq id
        }
    }
}