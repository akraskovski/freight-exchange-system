package com.github.akraskovski.fes.core.domain.service.user

import com.github.akraskovski.fes.core.domain.model.CombinedUserDetails
import com.github.akraskovski.fes.core.domain.model.User
import com.github.akraskovski.fes.core.domain.model.UserContacts
import com.github.akraskovski.fes.core.domain.model.UserInvite
import com.github.akraskovski.fes.core.domain.model.common.Gender
import com.github.akraskovski.fes.core.domain.repository.company.CompanyRepository
import com.github.akraskovski.fes.core.domain.repository.company.UserInviteRepository
import com.github.akraskovski.fes.core.domain.repository.security.AuthorizationRepository
import com.github.akraskovski.fes.core.domain.repository.user.UserRepository
import com.github.akraskovski.fes.core.domain.service.BasicOperationService
import com.github.akraskovski.fes.core.domain.service.CommonService
import com.github.akraskovski.fes.core.domain.service.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

const val UNKNOWN_VALUE: String = "UNKNOWN"

/**
 * Basic implementation of the UserService.
 */
@Service
class BasicUserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val userInviteRepository: UserInviteRepository,
    private val companyRepository: CompanyRepository,
    private val authorizationRepository: AuthorizationRepository
) : CommonService<User, String> by BasicOperationService(userRepository), UserService {

    override fun me(): CombinedUserDetails {
        val currentUserEmail: String = SecurityContextHolder.getContext().authentication.principal as? String
            ?: throw IllegalArgumentException("Couldn't determine current logged In user principal")
        val domainUser = userRepository.findByContactsEmail(currentUserEmail) ?: createDetails(currentUserEmail)
        val authServerUser = authorizationRepository.findById(domainUser.authProfileId)
            ?: handleUserNotFound(domainUser.authProfileId)

        return CombinedUserDetails.create(domainUser, authServerUser)
    }

    override fun registerAccount(user: User, token: String?): User {
        if (!authorizationRepository.isAccountRegistered(user.authProfileId)) {
            throw EntityNotFoundException("Registering account doesn't exist in authorization server")
        }

        return token?.let { applyInviteValues(user, it) } ?: save(user)
    }

    override fun findByEmail(email: String): User = userRepository.findByContactsEmail(email)
        ?: handleUserNotFound(email)

    private fun createDetails(email: String): User {
        val authServerUser = authorizationRepository.findByEmail(email) ?: handleUserNotFound(email)
        val userContacts = UserContacts(email)
        val user = User(authServerUser.id, UNKNOWN_VALUE, UNKNOWN_VALUE, Gender.UNKNOWN, userContacts)

        return save(user)
    }

    private fun handleUserNotFound(arg: String): Nothing = throw EntityNotFoundException("Couldn't find user: $arg")

    private fun applyInviteValues(user: User, token: String): User {
        return userInviteRepository.findByToken(token)?.let { processUserInvite(it, user) }
            ?: throw EntityNotFoundException("Invite token didn't exist")
    }

    private fun processUserInvite(userInvite: UserInvite, user: User): User {
        if (userInvite.expiresAt.isBefore(LocalDateTime.now())) {
            userInviteRepository.delete(userInvite)
            throw IllegalStateException("Invite token was expired")
        }

        return save(user)
            .apply { authorizationRepository.activateAccount(authProfileId) }
            .also {
                with(userInvite.company) {
                    employees.add(it)
                    companyRepository.save(this)
                }
            }
    }
}