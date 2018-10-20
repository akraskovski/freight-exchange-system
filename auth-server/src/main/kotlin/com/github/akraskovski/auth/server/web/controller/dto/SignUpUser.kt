package com.github.akraskovski.auth.server.web.controller.dto;

import com.github.akraskovski.auth.server.domain.model.Authority
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Data transfer object for registering new user.
 */
class SignUpUser(
        @field:NotBlank @field:Email var email: String,
        @field:NotBlank @field:Size(min = 8, max = 32) var password: String,
        @field:NotNull var authority: Authority
)

