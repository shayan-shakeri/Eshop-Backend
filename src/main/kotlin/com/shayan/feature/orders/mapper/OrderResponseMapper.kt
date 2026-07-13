package feature.orders.mapper

import com.shayan.feature.order.model.Order
import feature.orders.dto.OrderResponse

fun Order.toOrderResponse() =
    OrderResponse(
        id = id,
        userId = userId,
        addressId = addressId,
        deliveryState = deliveryState,
        deliveryDate = deliveryDate,
        originalPrice = originalPrice,
        finalPrice = finalPrice,
        paymentId = paymentId,
        port = port,
        createdAt = createdAt
    )