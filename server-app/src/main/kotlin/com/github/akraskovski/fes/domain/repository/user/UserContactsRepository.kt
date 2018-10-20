package com.github.akraskovski.fes.domain.repository.user

import com.github.akraskovski.fes.domain.model.UserContacts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * UserContacts data access layer.
 */
@Repository
interface UserContactsRepository : JpaRepository<UserContacts, String>