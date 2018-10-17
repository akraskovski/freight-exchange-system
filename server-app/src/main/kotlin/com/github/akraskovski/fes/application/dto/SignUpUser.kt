package com.github.akraskovski.fes.application.dto

import com.github.akraskovski.fes.domain.model.common.Gender
import javax.validation.constraints.*

/**
 * Dto for the user sign up properties.
 */
//TODO: add contacts dto
class SignUpUser(

        @NotBlank
        var id: String,

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
        var email: String)