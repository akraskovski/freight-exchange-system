package com.github.akraskovski.fes.web.controller

import com.github.akraskovski.fes.core.domain.model.User
import com.github.akraskovski.fes.core.domain.service.user.UserService
import com.github.akraskovski.fes.web.dto.IdDto
import com.github.akraskovski.fes.web.dto.ItemCountResponse
import com.github.akraskovski.fes.web.dto.search.SearchRequest
import com.github.akraskovski.fes.web.dto.search.SearchResponse
import com.github.akraskovski.fes.web.dto.user.ResponseUserDetails
import com.github.akraskovski.fes.web.dto.user.SignUpUser
import com.github.akraskovski.fes.web.mapping.toDTO
import com.github.akraskovski.fes.web.mapping.toSearchResponse
import com.github.akraskovski.fes.web.mapping.toUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
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
@RequestMapping("/api/v1/user")
class UserController @Autowired constructor(private val userService: UserService) {

    /**
     * Registering a new user account.
     */
    @PostMapping("/account/register")
    fun registerAccount(@RequestBody @Valid signUpUser: SignUpUser, @RequestParam token: String?): ResponseEntity<IdDto> =
        ResponseEntity.ok(IdDto(userService.registerAccount(signUpUser.toUser(), token).id!!))

    /**
     * Getting details about the current logged In user
     */
    @GetMapping("/me")
    fun me(): ResponseEntity<ResponseUserDetails> = ResponseEntity.ok(userService.me().toDTO())

    @PostMapping("/search")
    fun search(@RequestBody @Valid searchRequest: SearchRequest): ResponseEntity<SearchResponse<User>> {
        val searchResult = userService.search(searchRequest.text, searchRequest.toPageable())

        return ResponseEntity.ok(searchResult.toSearchResponse())
    }

    /**
     * Gets total count of the registered accounts.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/count")
    fun getTotalCount() = ResponseEntity.ok(ItemCountResponse(userService.totalCount()))
}