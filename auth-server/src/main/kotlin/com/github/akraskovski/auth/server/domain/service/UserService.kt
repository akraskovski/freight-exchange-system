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
     * Changes user account active status.
     */
    infix fun activateAccount(userId: String): User

    /**
     * Return current loggenIn user or throw exception if there is no authentication.
     */
    fun me(): User

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    infix fun getById(id: String): User

    /**
     * Find by email user or throws exception if there is no user with a given email
     *
     * @param email the email
     * @return the user
     */
    infix fun getByEmail(email: String): User

    /**
     * Find by email user. Returns null if user doesn't exist
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
