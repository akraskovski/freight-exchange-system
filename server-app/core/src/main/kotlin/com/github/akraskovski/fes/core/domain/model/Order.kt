package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * Domain order model.
 */
@Entity(name = "client_order")
class Order(
    val name: String,
    val description: String,
    val cost: Double,
    @OneToOne val assignee: User? = null,
    @OneToOne(cascade = [CascadeType.ALL]) val route: Route
) : AbstractEntity()