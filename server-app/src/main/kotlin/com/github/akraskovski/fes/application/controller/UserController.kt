package com.github.akraskovski.fes.application.controller

import com.github.akraskovski.fes.application.dto.IdDto
import com.github.akraskovski.fes.application.dto.SignUpUser
import com.github.akraskovski.fes.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * API endpoint for the users operations.
 */
@RestController
@RequestMapping("/api/v1/user")
class UserController @Autowired constructor(private val userService: UserService) {

    /**
     * Registering a new user account.
     */
    @PostMapping("/account/register")
    fun signUp(@RequestBody @Valid signUpUser: SignUpUser): ResponseEntity<IdDto> =
        ResponseEntity.ok(IdDto(userService.registerAccount(SignUpUser.toUser(signUpUser)).id!!))
}