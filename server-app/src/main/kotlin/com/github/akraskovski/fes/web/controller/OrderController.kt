package com.github.akraskovski.fes.web.controller

import com.github.akraskovski.fes.core.domain.service.order.OrderService
import com.github.akraskovski.fes.web.dto.IdDto
import com.github.akraskovski.fes.web.dto.order.RequestOrder
import com.github.akraskovski.fes.web.mapping.toOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * API endpoint for the order operations.
 */
@RestController
@RequestMapping("/api/v1/order")
class OrderController @Autowired constructor(private val orderService: OrderService) {

    /**
     * Create a new order.
     */
    @PreAuthorize("hasAnyRole('COMPANY_ADMIN','CLIENT')")
    @PostMapping
    fun create(@RequestBody @Valid requestOrder: RequestOrder): ResponseEntity<IdDto> = ResponseEntity.ok(IdDto(orderService.save(requestOrder.toOrder()).id!!))
}