package com.github.akraskovski.auth.server.web.controller;

import com.github.akraskovski.auth.server.domain.service.UserService
import com.github.akraskovski.auth.server.web.controller.dto.IdDto
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser
import com.github.akraskovski.auth.server.web.mapping.toUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * The type User controller.
 */
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService) {

    /**
     * Sign up response entity.
     *
     * @param signUpUser the sign up user
     * @return the response entity
     */
    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid signUpUser: SignUpUser): ResponseEntity<IdDto> = ResponseEntity.ok(IdDto((userService signUp signUpUser.toUser()).id!!))

    @PutMapping("/{userId}/activate")
    fun activateAccount(@PathVariable userId: String) {
        userService.activateAccount(userId)
    }
}

