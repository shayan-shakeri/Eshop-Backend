package com.shayan.feature.role.table

import com.shayan.feature.role.constants.RoleConst
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object RoleTable: Table(RoleConst.TABLE_NAME) {
    val id = varchar(RoleConst.ID, ANC.ID_LENGTH)
    val title = varchar(RoleConst.TITLE, RoleConst.TITLE_LENGTH)
    val desc = text(RoleConst.DESCRIPTION)
    val code = integer(RoleConst.CODE)

    override val primaryKey = PrimaryKey(id)
}