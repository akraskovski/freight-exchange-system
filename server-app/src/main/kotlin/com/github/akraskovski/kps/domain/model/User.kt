package com.github.akraskovski.kps.domain.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Domain User model.
 */
@Document
class User(
        id: String? = null,
        var firstname: String,
        var lastname: String,
        var age: Byte?,
        var phone: String?,
        var gender: Gender = Gender.UNKNOWN,
        var email: String,
        var password: String,
        var authority: Authority) : AbstractEntity(id)