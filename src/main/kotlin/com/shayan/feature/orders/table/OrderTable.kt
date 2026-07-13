package com.shayan.feature.order.table

import com.shayan.feature.address.table.AddressTable
import com.shayan.feature.order.constants.OrderConst
import com.shayan.feature.order.util.DeliveryState
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.javatime.timestamp

object OrderTable : Table(
    OrderConst.TABLE_NAME
) {

    val id =
        varchar(
            OrderConst.ID,
            ANC.ID_LENGTH
        )

    val userId =
        varchar(
            OrderConst.USER_ID,
            ANC.ID_LENGTH
        ).references(
            UsersTable.id
        )

    val addressId =
        varchar(
            OrderConst.ADDRESS_ID,
            ANC.ID_LENGTH
        ).references(
            AddressTable.id
        )

    val deliveryState =
        enumerationByName(
            OrderConst.DELIVERY_STATE,
            20,
            DeliveryState::class
        )

    val deliveryDate =
        datetime(
            OrderConst.DELIVERY_DATE
        )

    val originalPrice =
        decimal(
            OrderConst.ORIGINAL_PRICE,
            10,
            2
        )

    val finalPrice =
        decimal(
            OrderConst.FINAL_PRICE,
            10,
            2
        )

    val paymentId =
        varchar(
            OrderConst.PAYMENT_ID,
            ANC.ID_LENGTH
        )

    val port =
        varchar(
            OrderConst.PORT,
            OrderConst.PORT_LENGTH
        )

    val createdAt =
        timestamp(
            OrderConst.CREATED_AT
        )

    override val primaryKey =
        PrimaryKey(id)
}