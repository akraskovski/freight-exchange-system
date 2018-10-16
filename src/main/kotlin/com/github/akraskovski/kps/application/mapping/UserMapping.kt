package com.github.akraskovski.kps.application.mapping

import com.github.akraskovski.kps.application.dto.SignUpUser
import com.github.akraskovski.kps.application.dto.UserDetails
import com.github.akraskovski.kps.domain.model.Gender
import com.github.akraskovski.kps.domain.model.User

/**
 * Mapping SignUpUser Dto to the domain User model.
 */
fun SignUpUser.toUser() = User(
        firstname = this.firstname,
        lastname = this.lastname,
        age = this.age,
        phone = this.phone,
        gender = this.gender ?: Gender.UNKNOWN,
        email = this.email,
        password = this.password,
        authority = this.authority)

/**
 * Mapping domain User model the details Dto.
 */
fun fromUser(user: User): UserDetails = UserDetails(user.id!!, user.email, user.authority)