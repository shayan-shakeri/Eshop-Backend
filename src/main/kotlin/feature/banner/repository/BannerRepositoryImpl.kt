package com.shayan.feature.banner.repository

import com.shayan.feature.banner.mapper.toBanner
import com.shayan.feature.banner.model.Banner
import com.shayan.feature.banner.table.BannerTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class BannerRepositoryImpl : BannerRepository {

    override suspend fun add(
        banner: Banner
    ): Banner? {

        BannerTable.insert {
            it[id] = banner.id
            it[title] = banner.title
            it[active] = banner.active
            it[createdAt] = banner.createdAt
        }

        return findById(
            banner.id
        )
    }

    override suspend fun findById(
        id: String
    ): Banner? =
        BannerTable
            .selectAll()
            .where {
                BannerTable.id eq id
            }
            .singleOrNull()
            ?.toBanner()

    override suspend fun findActive():
            List<Banner> =
        BannerTable
            .selectAll()
            .where {
                BannerTable.active eq true
            }
            .map {
                it.toBanner()
            }

    override suspend fun readAll():
            List<Banner> =
        BannerTable
            .selectAll()
            .orderBy(
                BannerTable.createdAt,
                SortOrder.DESC
            )
            .map {
                it.toBanner()
            }

    override suspend fun update(
        banner: Banner
    ): Banner? {

        BannerTable.update(
            {
                BannerTable.id eq banner.id
            }
        ) {
            it[active] = banner.active
        }

        return findById(
            banner.id
        )
    }

    override suspend fun delete(
        id: String
    ) {

        BannerTable.deleteWhere {
            BannerTable.id eq id
        }
    }
}