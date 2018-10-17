package com.github.akraskovski.auth.server.domain.service;

import com.github.akraskovski.auth.server.domain.model.User;

import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Create user.
     *
     * @param user the user
     * @return the user
     */
    User signUp(User user);

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User getById(String id);

    /**
     * Find by email user.
     *
     * @param email the email
     * @return the user
     */
    Optional<User> findByEmail(String email);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);
}
