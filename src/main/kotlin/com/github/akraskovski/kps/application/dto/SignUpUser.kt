package com.github.akraskovski.kps.application.dto

import com.github.akraskovski.kps.domain.model.Authority
import com.github.akraskovski.kps.domain.model.Gender
import javax.validation.constraints.*

/**
 * Dto for the user sign up properties.
 */
class SignUpUser(
        @NotBlank
        var firstname: String,

        @NotBlank
        var lastname: String,

        @Min(10)
        @Max(100)
        var age: Byte?,

        var phone: String?,

        var gender: Gender?,

        @Email
        var email: String,

        @NotBlank
        @Size(min = 8)
        var password: String,

        @NotNull
        var authority: Authority)