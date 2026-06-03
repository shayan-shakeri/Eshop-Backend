package com.shayan.feature.address.repository

import com.shayan.feature.address.model.Address

interface AddressRepository {
    suspend fun addAddress(address: Address): Address?

    suspend fun findMainAddress(userId: String): Address?
    suspend fun findAllAddresses(userId: String): List<Address>

    suspend fun setAddressAsActive(id: String, activate: Boolean): Address?
    suspend fun updateAddress(address: Address): Address?

    suspend fun deleteOneAddress(id: String)
    suspend fun deleteAllAddresses(userId: String)
}