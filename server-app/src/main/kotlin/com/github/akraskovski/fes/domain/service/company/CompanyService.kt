package com.github.akraskovski.fes.domain.service.company

import com.github.akraskovski.fes.domain.model.Company
import com.github.akraskovski.fes.domain.service.CommonService

/**
 * General Company service interface.
 */
interface CompanyService : CommonService<Company, String> {

    /**
     * Registration of the company account.
     */
    fun create(company: Company, ownerId: String): Company
}
