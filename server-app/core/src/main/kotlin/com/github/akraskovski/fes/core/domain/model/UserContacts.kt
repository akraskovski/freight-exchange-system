package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import javax.persistence.Entity

/**
 * User contacts domain model.
 */
@Entity
class UserContacts(
    val email: String,
    val phone: String? = null,
    val skype: String? = null,
    val facebook: String? = null,
    val linkedIn: String? = null,
    val telegram: Boolean? = null,
    val whatsUp: Boolean? = null
) : AbstractEntity()