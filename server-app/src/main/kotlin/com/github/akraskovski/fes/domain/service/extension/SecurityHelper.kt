package com.github.akraskovski.fes.domain.service.extension

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

/**
 * Security context helper operations.
 */
@Component
class SecurityHelper @Autowired constructor(private val userService: UserService) {

    /**
     * Fetch user from spring authentication and return domain user model.
     * Throws exception if user was not found.
     */
    fun getCurrentUser(): User = userService.findByEmail(SecurityContextHolder.getContext().authentication.principal as String)
}
