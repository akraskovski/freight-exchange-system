package com.github.akraskovski.kps.application.mapping

import com.github.akraskovski.kps.application.dto.SignUpUser
import com.github.akraskovski.kps.application.dto.UserDetails
import com.github.akraskovski.kps.domain.model.User

/**
 * Mapping SignUpUser Dto to the domain User model.
 */
fun SignUpUser.toUser() = User(null, this.email, this.password, this.authorities)

/**
 * Mapping domain User model the details Dto.
 */
fun fromUser(user: User): UserDetails = UserDetails(user.id!!, user.email, user.authorities)