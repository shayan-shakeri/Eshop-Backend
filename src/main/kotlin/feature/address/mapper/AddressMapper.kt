package com.shayan.feature.address.mapper

import com.shayan.feature.address.model.Address
import com.shayan.feature.address.table.AddressTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toAddress(): Address =
    Address(
        id = this[AddressTable.id],
        userId = this[AddressTable.userId],
        country = this[AddressTable.country],
        state = this[AddressTable.state],
        city = this[AddressTable.city],
        road = this[AddressTable.road],
        postalCode = this[AddressTable.postalCode],
        additionalInfo = this[AddressTable.additionalInfo],
        mainAddress = this[AddressTable.mainAddress]
    )