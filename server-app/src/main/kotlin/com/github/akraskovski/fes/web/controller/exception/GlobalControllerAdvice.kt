package com.github.akraskovski.fes.web.controller.exception

import com.github.akraskovski.fes.core.domain.service.exception.EntityNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * General exception handler.
 */
@ControllerAdvice
class GlobalControllerAdvice {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * Handle Errors during authentication
     */
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: EntityNotFoundException): ResponseEntity<Nothing> {
        log.warn(e.message, e)
        return ResponseEntity.notFound().build()
    }
}