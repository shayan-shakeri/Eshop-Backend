package com.shayan.feature.version_control.repository

import com.shayan.feature.version_control.mapper.toVersionControl
import com.shayan.feature.version_control.model.VersionControl
import com.shayan.feature.version_control.table.VersionControlTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class VersionControlRepositoryImpl :
    VersionControlRepository {

    override suspend fun add(
        versionControl: VersionControl
    ): VersionControl? {

        VersionControlTable.insert {
            it[id] = versionControl.id
            it[version] = versionControl.version
            it[active] = versionControl.active
            it[description] = versionControl.description
            it[forced] = versionControl.forced
        }

        return findById(
            versionControl.id
        )
    }

    override suspend fun findById(
        id: String
    ): VersionControl? =
        VersionControlTable
            .selectAll()
            .where {
                VersionControlTable.id eq id
            }
            .singleOrNull()
            ?.toVersionControl()

    override suspend fun findActive():
            VersionControl? =
        VersionControlTable
            .selectAll()
            .where {
                VersionControlTable.active eq true
            }
            .orderBy(
                VersionControlTable.createdAt,
                SortOrder.DESC
            )
            .firstOrNull()
            ?.toVersionControl()

    override suspend fun readAll():
            List<VersionControl> =
        VersionControlTable
            .selectAll()
            .orderBy(
                VersionControlTable.createdAt,
                SortOrder.DESC
            )
            .map {
                it.toVersionControl()
            }

    override suspend fun verify(
        version: String
    ): VersionControl? =
        VersionControlTable
            .selectAll()
            .where {
                (VersionControlTable.version eq version) and
                        (VersionControlTable.active eq true)
            }
            .singleOrNull()
            ?.toVersionControl()

    override suspend fun update(
        versionControl: VersionControl
    ): VersionControl? {

        VersionControlTable.update(
            {
                VersionControlTable.id eq
                        versionControl.id
            }
        ) {

            it[version] =
                versionControl.version

            it[active] =
                versionControl.active

            it[description] =
                versionControl.description

            it[forced] =
                versionControl.forced
        }

        return findById(
            versionControl.id
        )
    }

    override suspend fun delete(
        id: String
    ) {

        VersionControlTable.deleteWhere {
            VersionControlTable.id eq id
        }
    }
}