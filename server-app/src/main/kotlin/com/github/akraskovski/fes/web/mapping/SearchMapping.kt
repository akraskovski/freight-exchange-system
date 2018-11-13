package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.web.dto.search.SearchResponse
import org.springframework.data.domain.Page

fun <T, R> Page<T>.toSearchResponse(block: (Iterable<T>) -> List<R>) = SearchResponse(totalPages, totalElements, block(content))