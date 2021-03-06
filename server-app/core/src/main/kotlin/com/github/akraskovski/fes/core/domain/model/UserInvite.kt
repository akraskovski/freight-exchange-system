package com.github.akraskovski.fes.core.domain.model

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.ManyToOne

/**
 * User registration invite from company owner
 */
@Entity
class UserInvite(
    @ManyToOne val company: Company,
    val email: String,
    val token: String,
    var expiresAt: LocalDateTime
) : AbstractEntity()