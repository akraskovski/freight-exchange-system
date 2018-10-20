package com.github.akraskovski.fes.domain.service.user

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.repository.user.UserContactsRepository
import com.github.akraskovski.fes.domain.repository.user.UserRepository
import com.github.akraskovski.fes.domain.service.BasicOperationService
import com.github.akraskovski.fes.domain.service.CommonService
import com.github.akraskovski.fes.domain.service.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Basic implementation of the UserService.
 */
@Service
class BasicUserService @Autowired constructor(private val userRepository: UserRepository, private val userContactsRepository: UserContactsRepository)
    : CommonService<User, String> by BasicOperationService(userRepository), UserService {

    override fun registerAccount(user: User): User {
        userContactsRepository.save(user.contacts)

        return create(user)
    }

    override fun findByFirstNameAndLastName(firstName: String, lastName: String): User = userRepository.findByFirstNameAndLastName(firstName, lastName)
        ?: handleUserNotFound("$firstName $lastName")

    private fun handleUserNotFound(arg: String): Nothing = throw EntityNotFoundException("Couldn't find user: $arg")
}