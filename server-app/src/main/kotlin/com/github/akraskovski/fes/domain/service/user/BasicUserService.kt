package com.github.akraskovski.fes.domain.service.user

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.repository.company.UserInviteRepository
import com.github.akraskovski.fes.domain.repository.security.AuthorizationRepository
import com.github.akraskovski.fes.domain.repository.user.UserRepository
import com.github.akraskovski.fes.domain.service.BasicOperationService
import com.github.akraskovski.fes.domain.service.CommonService
import com.github.akraskovski.fes.domain.service.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * Basic implementation of the UserService.
 */
@Service
class BasicUserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val userInviteRepository: UserInviteRepository,
    private val authorizationRepository: AuthorizationRepository
) : CommonService<User, String> by BasicOperationService(userRepository), UserService {

    override fun registerAccount(user: User, token: String?): User {
        if (!authorizationRepository.isAccountRegistered(user.authProfileId)) {
            throw EntityNotFoundException("Registering account doesn't exist in authorization server")
        }

        token?.let { applyInviteValues(user, it) }

        return save(user)
    }

    override fun findByEmail(email: String): User = userRepository.findByContactsEmail(email)
        ?: handleUserNotFound(email)

    private fun handleUserNotFound(arg: String): Nothing = throw EntityNotFoundException("Couldn't find user: $arg")

    private fun applyInviteValues(user: User, token: String) {
        userInviteRepository.findByToken(token)
            ?.apply {
                if (expiresAt.isAfter(LocalDateTime.now())) {
                    userInviteRepository.delete(this)
                    throw IllegalStateException("Invite token was expired")
                }
            }
            ?.let {
                user.company = it.company
                authorizationRepository.activateAccount(user.authProfileId)
            }
    }
}