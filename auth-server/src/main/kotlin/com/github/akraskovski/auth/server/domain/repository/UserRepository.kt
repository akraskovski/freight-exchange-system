package com.github.akraskovski.auth.server.domain.repository

import com.github.akraskovski.auth.server.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Spring data JPA {@link User} repository.
 */
interface UserRepository : JpaRepository<User, String> {

    /**
     * Searches {@link User} full-matches for a given username.
     *
     * @param email input param
     * @return found object or null if not exists
     */
    fun findByEmail(email: String): User?
}
