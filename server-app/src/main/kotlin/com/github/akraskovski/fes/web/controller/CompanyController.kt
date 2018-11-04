package com.github.akraskovski.fes.web.controller

import com.github.akraskovski.fes.core.domain.service.company.CompanyService
import com.github.akraskovski.fes.web.dto.IdDto
import com.github.akraskovski.fes.web.dto.company.RegisterCompany
import com.github.akraskovski.fes.web.dto.user.UserInvite2Company
import com.github.akraskovski.fes.web.mapping.toCompany
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * API endpoint for the company operations.
 */
@RestController
@RequestMapping("/api/v1/company")
class CompanyController @Autowired constructor(private val companyService: CompanyService) {

    /**
     * Registering a new company account.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun create(@RequestBody @Valid registerCompany: RegisterCompany): ResponseEntity<IdDto> =
        ResponseEntity.ok(IdDto(companyService.create(registerCompany.toCompany(), registerCompany.ownerId).id!!))

    /**
     * Sends register invite to the current user company.
     */
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping("/invite")
    fun inviteUser(@RequestBody @Valid inviteDto: UserInvite2Company) {
        companyService.sendInvite(inviteDto.email)
    }
}