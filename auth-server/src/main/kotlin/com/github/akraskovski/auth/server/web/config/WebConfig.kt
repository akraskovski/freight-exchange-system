package com.github.akraskovski.auth.server.web.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * General web mvc configuration.
 */
@Configuration
class WebConfig {

    /**
     * Password encoder bean.
     *
     * @return created component
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}
