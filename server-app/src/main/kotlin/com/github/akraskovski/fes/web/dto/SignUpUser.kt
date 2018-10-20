package com.github.akraskovski.fes.web.dto

import com.github.akraskovski.fes.domain.model.common.Gender
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Dto for the user sign up properties.
 */
class SignUpUser(
    @field:NotBlank var authProfileId: String,
    @field:NotBlank var firstName: String,
    @field:NotBlank var lastName: String,
    @field:NotNull var gender: Gender,
    @field:Email var email: String,
    @field:NotBlank var phone: String,
    var skype: String?,
    var facebook: String?,
    var linkedIn: String?,
    var telegram: Boolean?,
    var whatsUp: Boolean?
)