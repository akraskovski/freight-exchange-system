package com.github.akraskovski.fes.domain.service.user

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.service.CommonService

/**
 * General User Service interface.
 */
interface UserService : CommonService<User, String> {

    /**
     * Registers an new user account linked to the auth user profile.
     */
    fun registerAccount(user: User): User

    /**
     * Find user by a given email or throws exception if user was not found.
     */
    fun findByEmail(email: String): User
}