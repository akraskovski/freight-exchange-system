package com.github.akraskovski.auth.server.domain.service;

import com.github.akraskovski.auth.server.domain.model.Authority
import com.github.akraskovski.auth.server.domain.model.User
import com.github.akraskovski.auth.server.domain.repository.UserRepository
import com.github.akraskovski.auth.server.domain.service.exception.EntityNotFoundException
import com.github.akraskovski.auth.server.domain.service.exception.PermissionDeniedException
import com.github.akraskovski.auth.server.domain.service.exception.UserAlreadyExistsException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * The type Basic user service.
 */
@Service
class BasicUserService @Autowired constructor(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) : UserService {

    override infix fun signUp(user: User): User {
        if (user.authority == Authority.ROLE_ADMIN) {
            throw PermissionDeniedException("You cannot register user with this role")
        }

        if (findByEmail(user.email!!) != null) {
            throw UserAlreadyExistsException(user.email!!)
        }

        user.isActive = false
        user.password = passwordEncoder.encode(user.password)

        return userRepository.save(user)
    }

    override infix fun activateAccount(userId: String): User {
        val user = getById(userId)
        user.isActive = user.isActive.not()

        return userRepository.save(user)
    }

    override infix fun getById(id: String): User = userRepository.findById(id).orElseThrow { EntityNotFoundException("Cannot find user by id: $id") }

    override infix fun findByEmail(email: String): User? = userRepository.findByEmail(email)

    override infix fun delete(id: String) = userRepository.deleteById(id)

}
