package com.github.akraskovski.fes.web.dto.search

import org.springframework.data.domain.PageRequest
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

// TODO: extend with sorting
class Sort()

/**
 * Common search request dto.
 */
class SearchRequest(
    @field:NotNull @field:PositiveOrZero val page: Int,
    @field:NotNull @field:PositiveOrZero val size: Int,
    val text: String?
) {
    fun toPageable() = PageRequest.of(page, size)
}