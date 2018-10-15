package com.github.akraskovski.kps.domain.service.user

import com.github.akraskovski.kps.domain.model.User
import com.github.akraskovski.kps.domain.service.CommonService

/**
 * General User Service interface.
 */
interface UserService : CommonService<User, String> {

    /**
     * Finds User by a given email.
     * Doesn't throw any not found exception.
     */
    fun findByEmail(email: String): User?
}