package com.github.akraskovski.fes.core.domain.repository.user

import com.github.akraskovski.fes.core.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Users data access layer.
 */
@Repository
interface UserRepository : JpaRepository<User, String> {

    /**
     * Find user by a given email.
     */
    fun findByContactsEmail(email: String): User?

    /**
     * Users search implementation by all necessary fields.
     */
    @Query(value = "SELECT u " +
        "FROM user_details u " +
        "WHERE UPPER(u.firstName) LIKE CONCAT('%',UPPER(:text),'%')" +
        "OR UPPER(u.lastName) LIKE CONCAT('%',UPPER(:text),'%')" +
        "OR UPPER(u.contacts.email) LIKE CONCAT('%',UPPER(:text),'%')")
    fun fullTextSearch(@Param("text") searchString: String, pageable: Pageable): Page<User>
}
