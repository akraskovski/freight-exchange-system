package com.github.akraskovski.fes.core.domain.repository.security

import com.github.akraskovski.fes.core.domain.model.AuthServerUser

/**
 * Interface, defining general synchronization processes with auth server API.
 */
interface AuthorizationRepository {

    /**
     * Finds authorization server user model by a given identifier.
     */
    fun findById(authServerId: String): AuthServerUser?

    /**
     * Finds authorization server user model by a given email.
     */
    fun findByEmail(email: String): AuthServerUser?

    /**
     * Check is account with a given id registered in auth server or not.
     */
    fun isAccountRegistered(userId: String): Boolean

    /**
     * Activate user account by a given id.
     */
    fun activateAccount(userId: String)
}