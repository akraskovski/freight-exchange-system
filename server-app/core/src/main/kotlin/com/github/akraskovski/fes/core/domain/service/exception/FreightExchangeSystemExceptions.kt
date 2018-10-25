package com.github.akraskovski.fes.core.domain.service.exception

/**
 * Entity already exists runtime exception.
 */
class EntityAlreadyExistsException(message: String = "Entity already exists") : RuntimeException(message)

/**
 * Custom runtime exception for the not found domain entity.
 */
class EntityNotFoundException(message: String = "Couldn't find entity") : RuntimeException(message)