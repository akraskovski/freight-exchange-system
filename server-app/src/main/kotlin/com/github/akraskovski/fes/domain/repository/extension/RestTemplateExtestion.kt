package com.github.akraskovski.fes.domain.repository.extension

import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import java.net.URI

/**
 * Custom implementation of GET request method with just one Authorization header.
 */
internal inline fun <reified T : Any> RestTemplate.getForEntityWithAuth(url: String, crossinline authorization: () -> String): ResponseEntity<T> {
    val requestEntity = RequestEntity.get(URI.create(url))
        .header("Authorization", authorization())
        .build()

    return exchange(requestEntity)
}