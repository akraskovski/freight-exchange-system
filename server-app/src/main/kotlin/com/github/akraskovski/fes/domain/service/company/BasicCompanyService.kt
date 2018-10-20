package com.github.akraskovski.fes.domain.service.company

import com.github.akraskovski.fes.domain.model.Company
import com.github.akraskovski.fes.domain.repository.company.CompanyRepository
import com.github.akraskovski.fes.domain.service.BasicOperationService
import com.github.akraskovski.fes.domain.service.CommonService
import com.github.akraskovski.fes.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Basic implementation of the CompanyService.
 */
@Service
class BasicCompanyService @Autowired constructor(
    private val companyRepository: CompanyRepository,
    private val userService: UserService
) : CommonService<Company, String> by BasicOperationService(companyRepository), CompanyService {

    override fun create(company: Company, ownerId: String): Company {
        val companyOwner = userService.findById(ownerId)

        company.isActive = false
        company.owner = companyOwner

        return create(company)
    }
}