package com.github.akraskovski.fes.domain.repository.company

import com.github.akraskovski.fes.domain.model.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Company data access layer.
 */
@Repository
interface CompanyRepository : JpaRepository<Company, String>