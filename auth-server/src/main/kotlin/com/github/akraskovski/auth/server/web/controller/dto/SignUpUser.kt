package com.github.akraskovski.auth.server.web.controller.dto;

import com.github.akraskovski.auth.server.domain.model.Authority
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Data transfer object for registering new user.
 */
class SignUpUser(@NotBlank var email: String, @NotBlank @Size(min = 8) var password: String, @NotNull var authority: Authority)

