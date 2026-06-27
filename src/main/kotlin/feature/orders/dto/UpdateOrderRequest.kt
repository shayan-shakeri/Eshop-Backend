package feature.orders.dto

import com.shayan.feature.order.util.DeliveryState
import kotlinx.serialization.Serializable
import util.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class UpdateOrderRequest(
    val id: String,
    val deliveryState: DeliveryState,
    @Serializable(with = LocalDateTimeSerializer::class)
    val deliveryDate: LocalDateTime,
    val ip: String
)