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
    id: String? = null,
    var firstname: String,
    var lastname: String,
    var age: Byte?,
    @Enumerated(value = EnumType.STRING)
    var gender: Gender = Gender.UNKNOWN,
    @OneToOne
    var contacts: UserContacts
) : AbstractEntity(id)