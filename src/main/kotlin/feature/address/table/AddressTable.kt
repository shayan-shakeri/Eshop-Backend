package com.shayan.feature.address.table

import com.shayan.feature.address.constants.AddressConst
import com.shayan.feature.users.table.UsersTable
import core.consts.ANC
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object AddressTable : Table(AddressConst.TABLE_NAME) {
    val id = varchar(AddressConst.USER_ID, ANC.ID_LENGTH)
    val userId = varchar(AddressConst.USER_ID, ANC.ID_LENGTH).references(
        UsersTable.id,
        onDelete = ReferenceOption.CASCADE,
        onUpdate = ReferenceOption.RESTRICT
    )
    val country = varchar(AddressConst.COUNTRY, AddressConst.COUNTRY_LENGTH)
    val state = varchar(AddressConst.STATE, AddressConst.STATE_LENGTH).nullable()
    val city = varchar(AddressConst.CITY, AddressConst.CITY_LENGTH)
    val road = varchar(AddressConst.ROAD, AddressConst.ROAD_LENGTH)
    val postalCode = varchar(AddressConst.POSTAL_CODE, AddressConst.POSTAL_CODE_LENGTH)
    val additionalInfo = text(AddressConst.ADDITIONAL_INFO).nullable()
    val mainAddress = bool(AddressConst.MAIN_ADDRESS)

    override val primaryKey = PrimaryKey(id)

}