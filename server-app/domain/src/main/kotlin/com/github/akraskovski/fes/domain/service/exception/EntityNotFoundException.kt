package com.github.akraskovski.fes.domain.service.exception

/**
 * Custom runtime exception for the not found domain entity.
 */
class EntityNotFoundException(message: String = "Couldn't find entity") : RuntimeException(message)