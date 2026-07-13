package com.shayan.feature.version_control.mapper

import com.shayan.feature.version_control.model.VersionControl
import com.shayan.feature.version_control.table.VersionControlTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toVersionControl() =
    VersionControl(
        id = this[VersionControlTable.id],
        version = this[VersionControlTable.version],
        active = this[VersionControlTable.active],
        description = this[VersionControlTable.description],
        forced = this[VersionControlTable.forced],
        createdAt = this[VersionControlTable.createdAt]
    )