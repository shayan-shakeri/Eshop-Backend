package feature.orders.mapper

import com.shayan.feature.order.model.Order
import com.shayan.feature.order.table.OrderTable
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toOrder() =
    Order(
        id = this[OrderTable.id],
        userId = this[OrderTable.userId],
        addressId = this[OrderTable.addressId],
        deliveryState = this[OrderTable.deliveryState],
        deliveryDate = this[OrderTable.deliveryDate],
        originalPrice = this[OrderTable.originalPrice],
        finalPrice = this[OrderTable.finalPrice],
        paymentId = this[OrderTable.paymentId],
        port = this[OrderTable.port],
        createdAt = this[OrderTable.createdAt]
    )