package com.github.akraskovski.fes.domain.repository.company

import com.github.akraskovski.fes.domain.model.Company
import com.github.akraskovski.fes.domain.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Company data access layer.
 */
@Repository
interface CompanyRepository : JpaRepository<Company, String> {

    /**
     * Find a company by a owner.
     */
    fun findByOwner(owner: User): Company?
}
