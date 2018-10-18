package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * Domain order model.
 */
@Entity(name = "client_order")
class Order(
    id: String,
    val name: String,
    val description: String,
    @OneToOne val assignee: User,
    val cost: Double
) : AbstractEntity(id)