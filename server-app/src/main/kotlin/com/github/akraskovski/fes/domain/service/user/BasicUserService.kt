package com.github.akraskovski.fes.domain.service.user

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.repository.UserRepository
import com.github.akraskovski.fes.domain.service.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Basic implementation of the UserService.
 */
@Service
class BasicUserService @Autowired constructor(val userRepository: UserRepository) : UserService by BasicUserService(userRepository) {

    override fun findByEmail(email: String): User = userRepository.findByEmail(email) ?: throw EntityNotFoundException()
}