package com.github.akraskovski.auth.server.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Domain model used for securing app.
 */
@Entity(name = "domain_user_details")
class User() {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    var id: String? = null

    @Column(unique = true)
    var email: String? = null

    var password: String? = null

    @Enumerated(EnumType.STRING)
    var authority: Authority? = null

    var isActive: Boolean = false

    constructor(email: String, password: String, authority: Authority) : this() {
        this.email = email
        this.password = password
        this.authority = authority
    }
}