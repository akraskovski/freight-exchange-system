package com.github.akraskovski.fes.web.dto.order

import java.time.LocalDateTime
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

/**
 * Create order data transfer model.
 */
class RequestOrder(
    @field:NotBlank val name: String,
    val description: String,
    @field:PositiveOrZero @field:NotNull val cost: Double,
    @field:NotBlank val assigneeId: String,
    @field:NotBlank val startPoint: String,
    @field:NotBlank val endPoint: String,
    @field:NotNull @field:Future val deadline: LocalDateTime
)