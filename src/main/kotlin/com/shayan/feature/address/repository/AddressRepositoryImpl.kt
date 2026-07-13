package com.shayan.feature.address.repository

import com.shayan.feature.address.mapper.toAddress
import com.shayan.feature.address.model.Address
import com.shayan.feature.address.table.AddressTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update

class AddressRepositoryImpl : AddressRepository {

    override suspend fun addAddress(address: Address): Address? {
        AddressTable.insert {
            it[AddressTable.id] = address.id
            it[AddressTable.userId] = address.userId
            it[AddressTable.country] = address.country
            it[AddressTable.state] = address.state
            it[AddressTable.city] = address.city
            it[AddressTable.road] = address.road
            it[AddressTable.postalCode] = address.postalCode
            it[AddressTable.additionalInfo] = address.additionalInfo
            it[AddressTable.mainAddress] = address.mainAddress
        }
        return AddressTable
            .selectAll()
            .where { AddressTable.id eq address.id }
            .singleOrNull()
            ?.toAddress()
    }

    override suspend fun findMainAddress(userId: String): Address? =
        AddressTable
            .selectAll()
            .where { (AddressTable.userId eq userId) and (AddressTable.mainAddress eq true) }
            .singleOrNull()
            ?.toAddress()


    override suspend fun findAllAddresses(userId: String): List<Address> =
        AddressTable
            .selectAll()
            .where { AddressTable.userId eq userId }
            .map { it.toAddress() }

    override suspend fun setAddressAsActive(
        id: String,
        activate: Boolean
    ): Address? {
        AddressTable.update({ AddressTable.id eq id }) {
            it[AddressTable.mainAddress] = activate
        }
        return AddressTable
            .selectAll()
            .where { (AddressTable.id eq id) and (AddressTable.mainAddress eq true) }
            .singleOrNull()
            ?.toAddress()
    }

    override suspend fun updateAddress(
        address: Address
    ): Address? {
        AddressTable.update({ AddressTable.id eq address.id }) {
            it[AddressTable.country] = address.country
            it[AddressTable.state] = address.state
            it[AddressTable.city] = address.city
            it[AddressTable.road] = address.road
            it[AddressTable.postalCode] = address.postalCode
            it[AddressTable.additionalInfo] = address.additionalInfo
        }
        return AddressTable
            .selectAll()
            .where { AddressTable.id eq address.id }
            .singleOrNull()
            ?.toAddress()
    }

    override suspend fun deleteOneAddress(id: String) {
        AddressTable.deleteWhere{ AddressTable.id eq id }
    }

    override suspend fun deleteAllAddresses(userId: String) {
        AddressTable.deleteWhere{ AddressTable.userId eq userId }
    }
}