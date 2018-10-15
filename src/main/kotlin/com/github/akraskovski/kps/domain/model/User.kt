package com.github.akraskovski.kps.domain.model

import org.springframework.data.mongodb.core.mapping.Document

/**
 * Domain User model.
 */
@Document
class User(
        id: String?,
        var email: String,
        var password: String,
        var authorities: Set<Authority>) : AbstractEntity(id)