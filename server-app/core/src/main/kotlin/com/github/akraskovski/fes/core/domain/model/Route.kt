package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import java.time.LocalDateTime
import javax.persistence.Entity

/**
 * Domain route model.
 */
@Entity
class Route(
    val startPoint: String,
    val endPoint: String,
    val deadline: LocalDateTime
) : AbstractEntity()