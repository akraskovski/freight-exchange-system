package com.github.akraskovski.auth.server.web.mapping

import com.github.akraskovski.auth.server.domain.model.User
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser

fun SignUpUser.toUser(): User = User(email, password, authority)