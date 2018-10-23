package com.github.akraskovski.fes.domain.repository.security

/**
 * Interface, defining general synchronization processes with auth server API.
 */
interface AuthorizationRepository {

    /**
     * Check is account with a given id registered in auth server or not.
     */
    fun isAccountRegistered(userId: String): Boolean

    /**
     * Activate user account by a given id.
     */
    fun activateAccount(userId: String)
}