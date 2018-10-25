package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.core.domain.model.Order
import com.github.akraskovski.fes.core.domain.model.Route
import com.github.akraskovski.fes.web.dto.RequestOrder

/**
 * Mapping from the request create order to the domain model.
 */
fun RequestOrder.toOrder(): Order {
    val route = Route(startPoint, endPoint, deadline)

    return Order(name, description, cost, route = route)
}