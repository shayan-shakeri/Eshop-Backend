package feature.orders.dto

import com.shayan.feature.order.util.DeliveryState
import com.shayan.util.serializer.InstantSerializer
import kotlinx.serialization.Serializable
import util.serializer.BigDecimalSerializer
import util.serializer.LocalDateTimeSerializer
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime

@Serializable
data class OrderResponse(
    val id: String,
    val userId: String,
    val addressId: String,
    val deliveryState: DeliveryState,

    @Serializable(with = LocalDateTimeSerializer::class)
    val deliveryDate: LocalDateTime,
    @Serializable(with = BigDecimalSerializer::class)

    val originalPrice: BigDecimal,
    @Serializable(with = BigDecimalSerializer::class)

    val finalPrice: BigDecimal,
    val paymentId: String,
    val port: String,

    @Serializable(with = InstantSerializer::class)
    val createdAt: Instant
)