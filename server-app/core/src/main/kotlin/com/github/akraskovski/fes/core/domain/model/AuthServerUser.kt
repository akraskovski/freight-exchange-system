package com.github.akraskovski.fes.core.domain.model

/**
 * Authorization server user representation model.
 */
class AuthServerUser(val id: String, val email: String, val active: Boolean, val authority: Authority)