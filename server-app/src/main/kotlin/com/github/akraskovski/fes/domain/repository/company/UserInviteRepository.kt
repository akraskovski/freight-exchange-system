package com.github.akraskovski.fes.domain.repository.company

import com.github.akraskovski.fes.domain.model.UserInvite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * User company invite data access layer.
 */
@Repository
interface UserInviteRepository : JpaRepository<UserInvite, String>