package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import javax.persistence.Entity

/**
 * User contacts domain model.
 */
@Entity
class UserContacts(
    id: String? = null,
    var email: String?,
    var phone: String?,
    var skype: String?,
    var facebook: String?,
    var linkedIn: String?,
    var telegram: Boolean?,
    var whatsUp: Boolean?
) : AbstractEntity(id)