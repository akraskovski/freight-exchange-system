package com.github.akraskovski.kps.application.dto

import com.github.akraskovski.kps.domain.model.Authority

/**
 * Dto for the user sign up properties.
 */
class SignUpUser(
        var email: String,
        var password: String,
        var authorities: Set<Authority>)