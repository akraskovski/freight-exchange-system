package com.github.akraskovski.auth.server.domain.service;

import com.github.akraskovski.auth.server.domain.model.User

/**
 * The interface User service.
 */
interface UserService {

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    infix fun signUp(user: User): User

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    infix fun getById(id: String): User

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    infix fun findByEmail(email: String): User?

    /**
     * Delete.
     *
     * @param id the id
     */
    infix fun delete(id: String)
}
