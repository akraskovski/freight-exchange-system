package com.github.akraskovski.auth.server.domain.service.exception;

/**
 * The type User already exists exception.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String email) {
        super(String.format("User with email %s already exists", email));
    }
}
