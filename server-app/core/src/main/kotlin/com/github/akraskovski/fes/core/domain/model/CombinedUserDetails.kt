package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.Gender

/**
 * Resource serve domain user model with an additional authorization server user details.
 */
class CombinedUserDetails(val id: String?,
                          val authProfileId: String,
                          val firstName: String,
                          val lastName: String,
                          val gender: Gender,
                          val email: String,
                          val active: Boolean,
                          val authority: Authority,
                          val phone: String,
                          val skype: String?,
                          val facebook: String?,
                          val linkedIn: String?,
                          val telegram: Boolean?,
                          val whatsUp: Boolean?) {

    /**
     * Factory companion object.
     */
    companion object Factory {

        /**
         * Factory method for the CombinedUserDetails model.
         */
        fun create(user: User, authServerUser: AuthServerUser) =
            CombinedUserDetails(user.id,
                user.authProfileId,
                user.firstName,
                user.lastName,
                user.gender,
                user.contacts.email,
                authServerUser.active,
                authServerUser.authority,
                user.contacts.phone,
                user.contacts.skype,
                user.contacts.facebook,
                user.contacts.linkedIn,
                user.contacts.telegram,
                user.contacts.whatsUp)
    }
}