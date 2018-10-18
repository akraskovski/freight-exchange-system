package com.github.akraskovski.fes.domain.model

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import com.github.akraskovski.fes.domain.model.common.Gender
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

/**
 * Domain User model.
 */
@Entity(name = "user_details")
class User(
    id: String?,
    val authProfileId: String,
    val firstName: String,
    val lastName: String,
    @Enumerated(value = EnumType.STRING) val gender: Gender,
    @OneToOne val contacts: UserContacts
) : AbstractEntity(id)