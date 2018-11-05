package com.github.akraskovski.fes.web.dto.user

import com.github.akraskovski.fes.core.domain.model.common.Gender
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
    var skype: String? = null,
    var facebook: String? = null,
    var linkedIn: String? = null,
    var telegram: Boolean? = null,
    var whatsUp: Boolean? = null
)