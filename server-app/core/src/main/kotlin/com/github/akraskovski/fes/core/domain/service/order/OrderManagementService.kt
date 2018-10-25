package com.github.akraskovski.fes.core.domain.service.order

import com.github.akraskovski.fes.core.domain.model.Order
import com.github.akraskovski.fes.core.domain.repository.order.OrderRepository
import com.github.akraskovski.fes.core.domain.repository.order.RouteRepository
import com.github.akraskovski.fes.core.domain.service.BasicOperationService
import com.github.akraskovski.fes.core.domain.service.CommonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Basic implementation of the OrderService.
 */
@Service
class OrderManagementService @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val routeRepository: RouteRepository
) : CommonService<Order, String> by BasicOperationService(orderRepository), OrderService