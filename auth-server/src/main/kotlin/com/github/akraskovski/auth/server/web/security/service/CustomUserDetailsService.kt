package com.github.akraskovski.auth.server.web.security.service

import com.github.akraskovski.auth.server.domain.model.Authority
import com.github.akraskovski.auth.server.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

import com.github.akraskovski.auth.server.domain.model.User as DomainUser
import org.springframework.security.core.userdetails.User as SpringSecurityUser

/**
 * Implementation of the Spring Security {@link UserDetailsService}.
 */
@Service
class CustomUserDetailsService @Autowired constructor(val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails {
        val domainUser: DomainUser = userService.findByEmail(email!!)
                ?: throw UsernameNotFoundException("Cannot find user with email: $email")

        return SpringSecurityUser
                .withUsername(domainUser.email)
                .password(domainUser.password)
                .authorities(this convertAuthority domainUser.authority!!)
                .disabled(domainUser.isActive.not())
                .build()
    }

    private infix fun convertAuthority(userAuthority: Authority) =
            Collections.singletonList(SimpleGrantedAuthority(userAuthority.name))
}
