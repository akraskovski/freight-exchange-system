package com.github.akraskovski.fes.domain.repository

import com.github.akraskovski.fes.domain.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Users DAO.
 */
@Repository
interface UserRepository : MongoRepository<User, String> {

    /**
     * Find user by a given email in database.
     */
    fun findByEmail(email: String): User?
}
