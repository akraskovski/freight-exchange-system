package com.github.akraskovski.auth.server.web.controller.dto

import com.github.akraskovski.auth.server.domain.model.Authority

/**
 * Dto for the User details.
 */
class UserDetails(val id: String, val email: String, val isActive: Boolean, val authority: Authority)