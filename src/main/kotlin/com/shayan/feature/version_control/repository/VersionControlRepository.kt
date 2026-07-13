package com.shayan.feature.version_control.repository

import com.shayan.feature.version_control.model.VersionControl

interface VersionControlRepository {

    suspend fun add(
        versionControl: VersionControl
    ): VersionControl?

    suspend fun findById(
        id: String
    ): VersionControl?

    suspend fun findActive(): VersionControl?

    suspend fun readAll(): List<VersionControl>

    suspend fun verify(
        version: String
    ): VersionControl?

    suspend fun update(
        versionControl: VersionControl
    ): VersionControl?

    suspend fun delete(
        id: String
    )
}