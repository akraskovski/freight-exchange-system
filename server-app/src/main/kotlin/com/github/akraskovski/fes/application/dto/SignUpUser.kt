package com.github.akraskovski.fes.application.dto

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.model.UserContacts
import com.github.akraskovski.fes.domain.model.common.Gender
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * Dto for the user sign up properties.
 */
class SignUpUser(
    @field:NotBlank var authProfileId: String,
    @field:NotBlank var firstname: String,
    @field:NotBlank var lastname: String,
    @field:NotNull var gender: Gender,
    @field:Email var email: String,
    @field:NotBlank var phone: String,
    var skype: String?,
    var facebook: String?,
    var linkedIn: String?,
    var telegram: Boolean?,
    var whatsUp: Boolean?
) {
    companion object Mapper {
        fun toUser(dto: SignUpUser): User {
            val userContacts = UserContacts(null, dto.email, dto.phone, dto.skype, dto.facebook, dto.linkedIn, dto.telegram, dto.whatsUp)
            return User(null, dto.authProfileId, dto.firstname, dto.lastname, dto.gender, userContacts)
        }
    }
}