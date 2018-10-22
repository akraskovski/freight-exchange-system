package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.domain.model.User
import com.github.akraskovski.fes.domain.model.UserContacts
import com.github.akraskovski.fes.web.dto.SignUpUser

/**
 * Mapping from the SignUpUser dto to the domain user model.
 */
fun SignUpUser.toUser(): User {
    val userContacts = UserContacts(null, this.email, this.phone, this.skype, this.facebook, this.linkedIn, this.telegram, this.whatsUp)
    return User(null, this.authProfileId, this.firstName, this.lastName, this.gender, userContacts, null)
}