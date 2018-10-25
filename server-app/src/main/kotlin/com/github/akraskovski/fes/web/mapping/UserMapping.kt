package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.core.domain.model.User
import com.github.akraskovski.fes.core.domain.model.UserContacts
import com.github.akraskovski.fes.web.dto.SignUpUser

/**
 * Mapping from the SignUpUser dto to the domain user model.
 */
fun SignUpUser.toUser(): User {
    val userContacts = UserContacts(email, phone, skype, facebook, linkedIn, telegram, whatsUp)

    return User(authProfileId, firstName, lastName, gender, userContacts)
}