package com.shayan.feature.address.mapper

import com.shayan.feature.address.dto.ReadAddress
import com.shayan.feature.address.model.Address

fun Address.toReadAddress(): ReadAddress = ReadAddress(
    id = this.id,
    userId = this.userId,
    country = this.country,
    state = this.state,
    city = this.city,
    road = this.road,
    postalCode = this.postalCode,
    additionalInfo = this.additionalInfo,
    mainAddress = this.mainAddress
)