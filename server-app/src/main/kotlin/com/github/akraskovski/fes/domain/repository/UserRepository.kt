package com.github.akraskovski.fes.domain.repository

import com.github.akraskovski.fes.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Users DAO.
 */
@Repository
interface UserRepository : JpaRepository<User, String> {

    /**
     * Find user by a given email in database.
     */
    fun findByEmail(email: String): User?
}