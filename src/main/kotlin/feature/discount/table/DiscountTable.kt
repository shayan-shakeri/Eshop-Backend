package com.shayan.feature.discount.table

import com.shayan.feature.discount.constants.DiscountConst
import com.shayan.feature.discount.util.DiscountCondition
import core.consts.ANC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object DiscountTable : Table(DiscountConst.TABLE_NAME) {

    val id = varchar(
        DiscountConst.ID,
        ANC.ID_LENGTH
    )

    val productId = varchar(
        DiscountConst.PRODUCT_ID,
        ANC.ID_LENGTH
    )

    val userId = varchar(
        DiscountConst.USER_ID,
        ANC.ID_LENGTH
    ).nullable()

    val value = integer(
        DiscountConst.VALUE
    )

    val quantity = integer(
        DiscountConst.QUANTITY
    )

    val conditions =
        enumerationByName<DiscountCondition>(
            DiscountConst.CONDITIONS,
            DiscountConst.CONDITION_LENGTH
        ).nullable()

    val conditionValue = integer(
        DiscountConst.CONDITION_VALUE
    ).nullable()

    val active = bool(
        DiscountConst.ACTIVE
    )

    val endingDate = datetime(
        DiscountConst.ENDING_DATE
    ).nullable()

    override val primaryKey =
        PrimaryKey(id)
}