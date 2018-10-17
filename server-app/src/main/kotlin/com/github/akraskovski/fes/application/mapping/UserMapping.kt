package com.github.akraskovski.fes.application.mapping

import com.github.akraskovski.fes.application.dto.SignUpUser
import com.github.akraskovski.fes.application.dto.UserDetails
import com.github.akraskovski.fes.domain.model.User

/**
 * Mapping SignUpUser Dto to the domain User model.
 */
//TODO: add mapping for the newely created dtos.
//fun SignUpUser.toUser() = User(
//        firstname = this.firstname,
//        lastname = this.lastname,
//        age = this.age,
//        gender = this.gender ?: Gender.UNKNOWN)
fun SignUpUser.toUser() = null

/**
 * Mapping domain User model the details Dto.
 */
fun fromUser(user: User): UserDetails = UserDetails(user.id!!, user.contacts.email!!)