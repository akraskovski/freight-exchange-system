package com.github.akraskovski.fes.application.dto

import com.github.akraskovski.fes.domain.model.Authority

/**
 * Dto for the User details representation.
 */
class UserDetails(val id: String, var email: String, var authority: Authority)