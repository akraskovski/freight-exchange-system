package com.github.akraskovski.auth.server.web.controller.exception

import com.github.akraskovski.auth.server.domain.service.exception.UserAlreadyExistsException
import com.github.akraskovski.auth.server.web.controller.dto.ErrorResponse
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
    @ExceptionHandler(UserAlreadyExistsException::class)
    fun handleUserAlreadyExistsException(e: UserAlreadyExistsException): ResponseEntity<ErrorResponse> {
        log.warn(e.message, e)
        return ResponseEntity.badRequest().body(ErrorResponse(e.message!!))
    }
}