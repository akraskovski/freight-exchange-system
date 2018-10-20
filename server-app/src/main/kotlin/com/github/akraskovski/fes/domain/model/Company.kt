package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * Domain company model.
 */
@Entity
class Company(
    id: String?,
    val name: String,
    val description: String,
    @OneToOne var owner: User?,
    var isActive: Boolean?
) : AbstractEntity(id)