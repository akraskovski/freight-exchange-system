package com.github.akraskovski.kps.application.dto

import com.github.akraskovski.kps.domain.model.Authority

/**
 * Dto for the User details representation.
 */
class UserDetails(val id: String, var email: String, var authority: Authority)