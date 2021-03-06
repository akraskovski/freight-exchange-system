package com.github.akraskovski.fes.web.dto.user

import com.github.akraskovski.fes.core.domain.model.Authority
import com.github.akraskovski.fes.core.domain.model.common.Gender

/**
 * Dto for the user model with an additional auth server parameters.
 */
class ResponseUserDetails(
    val id: String?,
    val authProfileId: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val email: String,
    val active: Boolean? = null,
    val authority: Authority? = null,
    val phone: String?,
    val skype: String?,
    val facebook: String?,
    val linkedIn: String?,
    val telegram: Boolean?,
    val whatsUp: Boolean?
)