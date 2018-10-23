package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToMany
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
    @OneToMany var employees: MutableList<User>,
    var isActive: Boolean?
) : AbstractEntity(id)