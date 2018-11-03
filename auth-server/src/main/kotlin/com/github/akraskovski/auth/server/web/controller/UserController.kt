package com.github.akraskovski.auth.server.web.controller

import com.github.akraskovski.auth.server.domain.service.UserService
import com.github.akraskovski.auth.server.web.controller.dto.IdDto
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser
import com.github.akraskovski.auth.server.web.controller.dto.UserDetails
import com.github.akraskovski.auth.server.web.mapping.toUser
import com.github.akraskovski.auth.server.web.mapping.toUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
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
@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService) {

    /**
     * Sign up response entity.
     *
     * @param signUpUser the sign up user
     * @return the response entity
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid signUpUser: SignUpUser): ResponseEntity<IdDto> = ResponseEntity.ok(IdDto((userService signUp signUpUser.toUser()).id!!))

    /**
     * Active or deactivate user account depends on the current account state.
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{userId}/activate")
    fun activateAccount(@PathVariable userId: String) {
        userService.activateAccount(userId)
    }

    /**
     * Loading user details by a given id.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    fun getDetailsById(@PathVariable userId: String): ResponseEntity<UserDetails> = ResponseEntity.ok(userService.getById(userId).toUserDetails())

    /**
     * Loading user details by a given id.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/email/{email}")
    fun getDetailsByEmail(@PathVariable email: String): ResponseEntity<UserDetails> = ResponseEntity.ok(userService.getByEmail(email).toUserDetails())

    /**
     * Loading current loggedIn user details.
     */
    @GetMapping("/me")
    fun me(): ResponseEntity<UserDetails> = ResponseEntity.ok(userService.me().toUserDetails())
}

