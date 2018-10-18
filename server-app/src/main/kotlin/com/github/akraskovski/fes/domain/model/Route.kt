package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * Domain route model.
 */
@Entity
class Route(
    id: String,
    val startPoint: String,
    val endPoint: String,
    @OneToOne val order: Order
) : AbstractEntity(id)