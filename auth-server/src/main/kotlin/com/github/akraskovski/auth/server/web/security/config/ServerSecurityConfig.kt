package com.github.akraskovski.auth.server.web.security.config;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

/**
 * General Authorization server application security configuration.
 */
@Configuration
@EnableWebSecurity
class ServerSecurityConfig @Autowired constructor(val customUserDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/sign-up").permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(customUserDetailsService)
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
