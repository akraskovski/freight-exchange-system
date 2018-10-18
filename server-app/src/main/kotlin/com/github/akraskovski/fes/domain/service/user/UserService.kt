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
     * Finds User by a given first name and last name.
     * Throws exception if user was not found.
     */
    fun findByFirstNameAndLastName(firstName: String, lastName: String): User
}