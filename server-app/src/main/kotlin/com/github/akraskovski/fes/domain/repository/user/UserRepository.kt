package com.github.akraskovski.fes.domain.repository.user

import com.github.akraskovski.fes.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Users data access layer.
 */
@Repository
interface UserRepository : JpaRepository<User, String> {

    /**
     * Find user by a given email in database.
     */
    fun findByFirstNameAndLastName(firstName: String, lastName: String): User?
}
