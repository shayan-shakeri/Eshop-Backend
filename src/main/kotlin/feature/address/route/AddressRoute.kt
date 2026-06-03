package com.shayan.feature.address.route

import com.shayan.feature.address.constants.AddressConst
import com.shayan.feature.address.dto.AddAddress
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.address.dto.UpdateAddress
import com.shayan.feature.address.service.AddressService
import com.shayan.util.callUtil.idExtractor
import core.consts.AppConstants
import core.util.extractFromParam
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.addressRoute(
    addressService: AddressService
) {
    route(AddressConst.MAIN_ROUTE){
        authenticate(AppConstants.Jwt.ACCESS_AUTH) {

            get(AddressConst.READ_ROUTE) {
                val ip = call.extractFromParam(AddressConst.IP_PARAM)
                val id = call.idExtractor()
                call.respond(addressService.getAllAddresses(id, ip))
            }

            post(AddressConst.ADD_ROUTE) {
                val request = call.receive<AddAddress>()
                call.respond(addressService.addAddress(request))
            }

            put(AddressConst.UPDATE_INFO_ROUTE) {
                val request = call.receive<UpdateAddress>()
                val id = call.idExtractor()
                call.respond(addressService.updateAddress(request, id))
            }

            put(AddressConst.UPDATE_ACTIVE_ADDRESS_ROUTE) {
                val request = call.receive<IdIpDTO>()
                val id = call.idExtractor()
                call.respond(addressService.setAddressAsActive(id, request))
            }

            delete(AddressConst.DELETE_SINGLE_ROUTE) {
                val request = call.receive<IdIpDTO>()
                val id = call.idExtractor()
                call.respond(addressService.deleteAddress(request, id))
            }

            delete(AddressConst.DELETE_ALL_ROUTE) {
                val ip = call.extractFromParam(AddressConst.IP_PARAM)
                val id = call.idExtractor()
                call.respond(addressService.deleteAllAddresses(id, ip))
            }
        }
    }
}