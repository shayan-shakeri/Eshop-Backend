package com.shayan.feature.address.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.feature.address.constants.AddressConst
import com.shayan.feature.address.dto.AddAddress
import com.shayan.feature.address.dto.ReadAddress
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.address.dto.UpdateAddress
import com.shayan.feature.address.mapper.toReadAddress
import com.shayan.feature.address.model.Address
import com.shayan.feature.address.repository.AddressRepository
import com.shayan.feature.audit_logs.service.AuditLogService
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException

class AddressService(
    private val addressRepository: AddressRepository,
    private val auditLogService: AuditLogService
) {

    suspend fun addAddress(addAddress: AddAddress): ReadAddress {
        runCatching { auditLogService.add(addAddress.userId, AddressConst.ADD_ACTION, addAddress.ip) }
        return dbQuery {
            if (addAddress.mainAddress) {
                val findMainAddress = addressRepository.findMainAddress(addAddress.userId)
                findMainAddress?.let { addressRepository.setAddressAsActive(it.id, false) }
            }

            val id = IdGenerator.generate()

            val address = Address(
                id = id,
                userId = addAddress.userId,
                country = addAddress.country,
                state = addAddress.state,
                city = addAddress.city,
                road = addAddress.road,
                postalCode = addAddress.postalCode,
                additionalInfo = addAddress.additionalInfo,
                mainAddress = addAddress.mainAddress,
            )

            addressRepository.addAddress(address)?.toReadAddress() ?: throw FailedToAdd()

        }
    }

    suspend fun getAllAddresses(userId: String, ip: String): List<Address> {
        runCatching { auditLogService.add(userId, AddressConst.READ_ACTION, ip) }
        return dbQuery { addressRepository.findAllAddresses(userId) }
    }


    suspend fun setAddressAsActive(userId: String, idIpDTO: IdIpDTO): ReadAddress {
        runCatching { auditLogService.add(userId, AddressConst.UPDATE_ACTIVE_ADDRESS_ACTION, idIpDTO.ip) }
        return dbQuery {
            val findMainAddress = addressRepository.findMainAddress(userId)
            findMainAddress?.let { addressRepository.setAddressAsActive(it.id, false) }
            addressRepository.setAddressAsActive(idIpDTO.id, true)?.toReadAddress() ?: throw NotFoundException()
        }
    }

    suspend fun updateAddress(updateAddress: UpdateAddress, userId: String): ReadAddress  {
        runCatching { auditLogService.add(userId, AddressConst.UPDATE_ACTION, updateAddress.ip) }
        return dbQuery {
            val address = Address(
                id = updateAddress.id,
                userId = "",
                country = updateAddress.country,
                state = updateAddress.state,
                city = updateAddress.city,
                road = updateAddress.road,
                postalCode = updateAddress.postalCode,
                additionalInfo = updateAddress.additionalInfo,
                mainAddress = updateAddress.mainAddress,
            )
            addressRepository.updateAddress(address)?.toReadAddress() ?: throw NotFoundException()
        }
    }

    suspend fun deleteAddress(idIpDTO: IdIpDTO, userId: String){
        runCatching { auditLogService.add(userId, AddressConst.DELETE_SINGLE_ACTION, idIpDTO.ip) }
        addressRepository.deleteOneAddress(idIpDTO.id)
    }

    suspend fun deleteAllAddresses(userId: String, ip: String){
        runCatching { auditLogService.add(userId, AddressConst.DELETE_ALL_ACTION, ip) }
        addressRepository.deleteAllAddresses(userId)
    }
}