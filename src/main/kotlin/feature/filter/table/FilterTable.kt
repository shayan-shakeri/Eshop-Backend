package com.shayan.feature.filter.table

import com.shayan.feature.category.constants.CategoryConst
import com.shayan.feature.filter.constants.FilterConst
import core.consts.ANC
import org.jetbrains.exposed.sql.Table

object FilterTable : Table(FilterConst.TABLE_NAME) {
    val id = varchar(FilterConst.ID, ANC.ID_LENGTH)
    val categoryId = varchar(FilterConst.CATEGORY_ID, ANC.ID_LENGTH)
    val name = varchar(FilterConst.NAME, FilterConst.NAME_LENGTH)
    val imageTitle = varchar(FilterConst.IMAGE_TITLE, FilterConst.IMAGE_TITLE_LENGTH)

    override val primaryKey = PrimaryKey(id)
}