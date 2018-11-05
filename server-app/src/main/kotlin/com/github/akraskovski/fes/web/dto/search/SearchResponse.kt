package com.github.akraskovski.fes.web.dto.search

/**
 * Common search response dto.
 */
class SearchResponse<T>(val totalPages: Int, val totalElements: Long, val content: List<T>)