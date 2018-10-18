package com.github.akraskovski.fes.application.dto

import com.github.akraskovski.fes.domain.model.common.Gender
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

/**
 * Dto for the user sign up properties.
 */
// TODO: add contacts dto
class SignUpUser(
    @field:NotBlank var id: String,
    @field:NotBlank var firstname: String,
    @field:NotBlank var lastname: String,
    @field:Min(10) @field:Max(100) var age: Byte?,
    var phone: String?,
    var gender: Gender?,
    @field:Email var email: String
)