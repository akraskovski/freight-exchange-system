package com.github.akraskovski.fes.application.controller

import com.github.akraskovski.fes.application.dto.IdDto
import com.github.akraskovski.fes.application.dto.SignUpUser
import com.github.akraskovski.fes.application.dto.UserDetails
import com.github.akraskovski.fes.application.mapping.fromUser
import com.github.akraskovski.fes.application.mapping.toUser
import com.github.akraskovski.fes.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * API endpoint for the users operations.
 */
@RestController
@RequestMapping("/api/fes/v1/user")
class UserController @Autowired constructor(private val userService: UserService) {

    /**
     * Registering a new user account.
     */
    @PostMapping("/account/register")
    fun signUp(@RequestBody @Valid signUpUser: SignUpUser): ResponseEntity<IdDto> =
            ResponseEntity.ok(IdDto(userService.register(signUpUser.toUser()!!).id!!))

    /**
     * Finds an User by a given id.
     * Return UserDetails if user was found otherwise 404 response status.
     */
    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<UserDetails> =
            ResponseEntity.ok(fromUser(userService.findById(id)))

    /**
     * Finds an User by a given id.
     * Return UserDetails if user was found otherwise 404 response status.
     */
    @GetMapping
    fun findByEmail(@RequestParam email: String): ResponseEntity<UserDetails> =
            ResponseEntity.ok(fromUser(userService.findByEmail(email)))
}