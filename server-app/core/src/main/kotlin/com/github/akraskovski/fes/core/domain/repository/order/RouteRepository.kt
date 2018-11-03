package com.github.akraskovski.fes.core.domain.repository.order

import com.github.akraskovski.fes.core.domain.model.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Route data access layer.
 */
@Repository
interface RouteRepository : JpaRepository<Route, String>