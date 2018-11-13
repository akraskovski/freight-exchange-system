package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.core.domain.model.CombinedUserDetails
import com.github.akraskovski.fes.core.domain.model.User
import com.github.akraskovski.fes.core.domain.model.UserContacts
import com.github.akraskovski.fes.web.dto.user.ResponseUserDetails
import com.github.akraskovski.fes.web.dto.user.SignUpUser

/**
 * Mapping from the SignUpUser dto to the domain user model.
 */
fun SignUpUser.toUser(): User {
    val userContacts = UserContacts(email, phone, skype, facebook, linkedIn, telegram, whatsUp)

    return User(authProfileId = authProfileId, firstName = firstName, lastName = lastName, gender = gender, contacts = userContacts)
}

/**
 * Mapping from the domain combined user details to the response dto.
 */
fun CombinedUserDetails.toDTO(): ResponseUserDetails =
    ResponseUserDetails(id, authProfileId, firstName, lastName, gender, email, active, authority, phone, skype, facebook, linkedIn, telegram, whatsUp)

/**
 * Mapping from the domain user details to the response dto.
 */
fun User.toDto(): ResponseUserDetails = ResponseUserDetails(
    id,
    authProfileId,
    firstName,
    lastName,
    gender,
    contacts.email,
    null,
    authority,
    contacts.phone,
    contacts.skype,
    contacts.facebook,
    contacts.linkedIn,
    contacts.telegram,
    contacts.whatsUp)