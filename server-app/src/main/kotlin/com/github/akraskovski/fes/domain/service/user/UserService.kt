package com.github.akraskovski.fes.domain.service.user

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.service.CommonService

/**
 * General User Service interface.
 */
interface UserService : CommonService<User, String> {

    /**
     * Finds User by a given email.
     * Throws exception if user was not found.
     */
    fun findByEmail(email: String): User
}