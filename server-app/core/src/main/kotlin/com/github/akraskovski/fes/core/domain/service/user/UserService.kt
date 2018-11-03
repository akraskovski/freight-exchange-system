package com.github.akraskovski.fes.core.domain.service.user

import com.github.akraskovski.fes.core.domain.model.CombinedUserDetails
import com.github.akraskovski.fes.core.domain.model.User
import com.github.akraskovski.fes.core.domain.service.CommonService

/**
 * General User Service interface.
 */
interface UserService : CommonService<User, String> {

    /**
     * Loads current user details with an additional data from auth server.
     */
    fun me(): CombinedUserDetails

    /**
     * Registers an new user account linked to the auth user profile.
     * Token is an optional parameter which could be attached in case of user invitation to the company during registering.
     */
    fun registerAccount(user: User, token: String? = null): User

    /**
     * Find user by a given email or throws exception if user was not found.
     */
    fun findByEmail(email: String): User
}