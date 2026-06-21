package com.shayan.feature.product.table

import com.shayan.feature.category.table.CategoryTable
import com.shayan.feature.filter.table.FilterTable
import com.shayan.feature.product.constants.ProductConst
import com.shayan.util.enums.ProductAge
import com.shayan.util.enums.ProductGender
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object ProductTable : Table(ProductConst.TABLE_NAME) {

    val id = varchar(
        ProductConst.ID,
        ANC.ID_LENGTH
    )

    val categoryId = reference(
        ProductConst.CATEGORY_ID,
        CategoryTable.id,
        onDelete = ReferenceOption.RESTRICT,
        onUpdate = ReferenceOption.RESTRICT
    )

    val filterId = reference(
        ProductConst.FILTER_ID,
        FilterTable.id,
        onDelete = ReferenceOption.RESTRICT,
        onUpdate = ReferenceOption.RESTRICT
    )

    val name = varchar(
        ProductConst.NAME,
        ProductConst.NAME_LENGTH
    )

    val description = text(
        ProductConst.DESCRIPTION
    )

    val originalPrice = decimal(
        ProductConst.ORIGINAL_PRICE,
        precision = 10,
        scale = 2
    )

    val price = decimal(
        ProductConst.PRICE,
        precision = 10,
        scale = 2
    )

    val stock = integer(
        ProductConst.STOCK
    )

    val size = varchar(
        ProductConst.SIZE,
        ProductConst.SIZE_LENGTH
    )

    val length = varchar(
        ProductConst.LENGTH,
        ProductConst.LENGTH_LENGTH
    )

    val material = varchar(
        ProductConst.MATERIAL,
        ProductConst.MATERIAL_LENGTH
    )

    val gender = enumerationByName<ProductGender>(
        ProductConst.GENDER,
        10
    )

    val age = enumerationByName<ProductAge>(
        ProductConst.AGE,
        20
    )

    override val primaryKey =
        PrimaryKey(id)
}