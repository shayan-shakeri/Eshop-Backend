package com.shayan.feature.category.table

import com.shayan.feature.category.constants.CategoryConst
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object CategoryTable: Table(CategoryConst.TABLE_NAME) {
    val id = varchar(CategoryConst.ID, ANC.ID_LENGTH)
    val name = varchar(CategoryConst.NAME, CategoryConst.NAME_LENGTH)

    override val primaryKey = PrimaryKey(id)
}