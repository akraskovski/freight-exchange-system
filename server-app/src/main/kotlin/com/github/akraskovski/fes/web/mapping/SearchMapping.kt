package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.web.dto.search.SearchResponse
import org.springframework.data.domain.Page

fun <T> Page<T>.toSearchResponse() = SearchResponse(totalPages, totalElements, content)