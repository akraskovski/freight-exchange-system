package com.github.akraskovski.fes.core.domain.repository.order

import com.github.akraskovski.fes.core.domain.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Order data access layer.
 */
@Repository
interface OrderRepository : JpaRepository<Order, String>