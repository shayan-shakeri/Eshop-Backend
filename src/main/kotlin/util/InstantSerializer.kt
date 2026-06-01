package com.shayan.util

import java.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

object InstantSerializer : KSerializer<Instant> {

    override val descriptor =
        PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun serialize(
        encoder: Encoder,
        value: Instant
    ) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(
        decoder: Decoder
    ): Instant {
        return Instant.parse(decoder.decodeString())
    }
}