package com.github.akraskovski.fes.web.mapping

import com.github.akraskovski.fes.domain.model.Company
import com.github.akraskovski.fes.web.dto.RegisterCompany

/**
 * Mapping from the RegisterCompany dto to the domain model.
 */
fun RegisterCompany.toCompany() = Company(null, this.name, this.description, null, mutableListOf(), false)