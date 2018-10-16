package com.github.akraskovski.kps.domain.service.user

import com.github.akraskovski.kps.domain.model.User
import com.github.akraskovski.kps.domain.repository.UserRepository
import com.github.akraskovski.kps.domain.service.exception.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Basic implementation of the UserService.
 */
@Service
class BasicUserService @Autowired constructor(val userRepository: UserRepository) : UserService {

    override fun register(obj: User): User = userRepository.insert(obj)

    override fun findById(id: String): User = userRepository.findById(id).orElseThrow { EntityNotFoundException() }

    override fun findByEmail(email: String): User = userRepository.findByEmail(email) ?: throw EntityNotFoundException()

    override fun findAll(): List<User> = userRepository.findAll()

    override fun update(obj: User): User = userRepository.save(obj)

    override fun delete(id: String) = userRepository.deleteById(id)

    override fun delete(obj: User) = userRepository.delete(obj)
}