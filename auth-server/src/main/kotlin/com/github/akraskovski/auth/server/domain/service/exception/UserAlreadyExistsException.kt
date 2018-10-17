package com.github.akraskovski.auth.server.domain.service.exception;

/**
 * The type User already exists exception.
 */
class UserAlreadyExistsException(email: String) : RuntimeException("User with email ${email} already exists")
