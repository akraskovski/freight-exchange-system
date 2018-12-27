package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.core.domain.model.Company
import com.github.akraskovski.fes.web.dto.company.RegisterCompany

/**
 * Mapping from the RegisterCompany dto to the domain model.
 */
fun RegisterCompany.toCompany() = Company(name, description, employees = mutableListOf(), isActive = false)