package com.github.akraskovski.kps.application.controller

import com.github.akraskovski.kps.application.dto.IdDto
import com.github.akraskovski.kps.application.dto.SignUpUser
import com.github.akraskovski.kps.application.dto.UserDetails
import com.github.akraskovski.kps.application.mapping.fromUser
import com.github.akraskovski.kps.application.mapping.toUser
import com.github.akraskovski.kps.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * API endpoint for the users operations.
 */
@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(private val userService: UserService) {

    /**
     * Registering a new user account.
     */
    @PostMapping("sign-up")
    fun signUp(@RequestBody signUpUser: SignUpUser): ResponseEntity<IdDto> =
            ResponseEntity.ok(IdDto(userService.create(signUpUser.toUser()).id!!))

    /**
     * Finds an User by a given id.
     * Return UserDetails if user was found otherwise 404 response status.
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<UserDetails> =
            userService.findById(id)
                    .map(::fromUser)
                    .map { ResponseEntity.ok(it) }
                    .orElse(ResponseEntity.notFound().build())

}

