package com.shayan.feature.role.mapper

import com.shayan.feature.role.model.Role
import com.shayan.feature.role.table.RoleTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toRole(): Role =
    Role(
        id = this[RoleTable.id],
        title = this[RoleTable.title],
        description = this[RoleTable.desc],
        code = this[RoleTable.code]
    )