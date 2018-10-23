package com.github.akraskovski.fes.web.dto

import javax.validation.constraints.NotBlank

/**
 * Company registration dto.
 */
class RegisterCompany(
    @field:NotBlank var name: String,
    @field:NotBlank var description: String,
    @field:NotBlank var ownerId: String
)