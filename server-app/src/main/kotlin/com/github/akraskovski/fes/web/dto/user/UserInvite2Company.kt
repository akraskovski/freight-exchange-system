package com.github.akraskovski.fes.web.dto.user

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 * Data transfer object defining properties to invite user to some company.
 */
class UserInvite2Company(@field:Email @NotBlank val email: String)