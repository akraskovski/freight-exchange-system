package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import com.github.akraskovski.fes.core.domain.model.common.Gender
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.OneToOne

/**
 * Domain User model.
 */
@Entity(name = "user_details")
class User(
    val authProfileId: String,
    val firstName: String,
    val lastName: String,
    @Enumerated(EnumType.STRING) var authority: Authority? = null,
    @Enumerated(EnumType.STRING) val gender: Gender,
    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REMOVE]) val contacts: UserContacts
) : AbstractEntity()