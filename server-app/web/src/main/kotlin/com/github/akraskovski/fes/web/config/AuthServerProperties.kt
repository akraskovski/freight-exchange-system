package com.github.akraskovski.fes.web.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Auth Server configuration properties container.
 */
@Configuration
@ConfigurationProperties(prefix = "auth.server")
class AuthServerProperties {

    var connection: Connection? = null
    var admin: Admin? = null

    class Connection {
        lateinit var url: String
        lateinit var clientId: String
        lateinit var clientSecret: String
    }

    class Admin {
        lateinit var email: String
        lateinit var password: String
    }
}