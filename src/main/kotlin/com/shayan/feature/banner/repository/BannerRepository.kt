package com.shayan.feature.banner.repository

import com.shayan.feature.banner.model.Banner

interface BannerRepository {

    suspend fun add(
        banner: Banner
    ): Banner?

    suspend fun findById(
        id: String
    ): Banner?

    suspend fun findActive():
            List<Banner>

    suspend fun readAll():
            List<Banner>

    suspend fun update(
        banner: Banner
    ): Banner?

    suspend fun delete(
        id: String
    )
}