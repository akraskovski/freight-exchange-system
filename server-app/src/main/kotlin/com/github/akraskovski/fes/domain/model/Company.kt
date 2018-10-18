package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import javax.persistence.Entity
import javax.persistence.OneToOne

/**
 * Domain company model.
 */
@Entity
class Company(
    id: String,
    val name: String,
    @OneToOne val owner: User,
    val isActive: Boolean
) : AbstractEntity(id)