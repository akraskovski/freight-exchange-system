package com.github.akraskovski.auth.server.web.mapping

import com.github.akraskovski.auth.server.domain.model.User
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser
import com.github.akraskovski.auth.server.web.controller.dto.UserDetails

fun SignUpUser.toUser(): User = User(email, password, authority)

fun User.toUserDetails(): UserDetails = UserDetails(this.id!!, this.email!!, this.isActive)