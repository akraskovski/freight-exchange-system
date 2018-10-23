package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import javax.persistence.Entity

/**
 * User contacts domain model.
 */
@Entity
class UserContacts(
    id: String?,
    val email: String,
    val phone: String,
    val skype: String?,
    val facebook: String?,
    val linkedIn: String?,
    val telegram: Boolean?,
    val whatsUp: Boolean?
) : AbstractEntity(id)