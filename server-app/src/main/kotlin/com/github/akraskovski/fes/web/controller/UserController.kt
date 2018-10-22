package com.github.akraskovski.fes.web.controller

import com.github.akraskovski.fes.domain.service.user.UserService
import com.github.akraskovski.fes.web.dto.IdDto
import com.github.akraskovski.fes.web.dto.SignUpUser
import com.github.akraskovski.fes.web.mapping.toUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/account/register")
    fun registerAccount(@RequestBody @Valid signUpUser: SignUpUser, @PathVariable token: String?): ResponseEntity<IdDto> =
        ResponseEntity.ok(IdDto(userService.registerAccount(signUpUser.toUser()).id!!))
}