package com.shayan.feature.product_image.table

import com.shayan.feature.product.table.ProductTable
import com.shayan.feature.product_image.constants.ProductImageConst
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object ProductImageTable : Table(ProductImageConst.TABLE_NAME) {

    val id = varchar(
        ProductImageConst.ID,
        ANC.ID_LENGTH
    )

    val productId = varchar(
        ProductImageConst.PRODUCT_ID_DB,
        ANC.ID_LENGTH
    ).references(
        ProductTable.id,
        onUpdate = ReferenceOption.RESTRICT,
        onDelete = ReferenceOption.CASCADE
    )

    val previewImage = bool(
        ProductImageConst.PREVIEW_IMAGE
    )

    val title = varchar(
        ProductImageConst.TITLE,
        ProductImageConst.TITLE_LENGTH
    )

    override val primaryKey = PrimaryKey(id)
}