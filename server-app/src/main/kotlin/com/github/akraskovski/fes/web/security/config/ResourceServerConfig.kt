package com.github.akraskovski.fes.web.security.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.security.oauth2.provider.token.RemoteTokenServices

const val BASE_API_URL = "/api/v1"

/**
 * Configuration this module as an OAuth2.0 Resource Server instance.
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Value("\${resource.id:company_api}")
    lateinit var resourceId: String

    @Value("\${auth.server.connection.url}")
    lateinit var authServerUrl: String

    @Value("\${auth.server.connection.clientId}")
    lateinit var clientId: String

    @Value("\${auth.server.connection.clientSecret}")
    lateinit var clientSecret: String

    /**
     * {@link RemoteTokenServices} custom configuration for the remote connection to the Authorization Server.
     * Used for verifying incoming tokens in requests to the resource server.
     *
     * @return configured remote token service
     */
    @Bean
    @Primary
    fun remoteTokenServices(): RemoteTokenServices {
        val remoteTokenServices = RemoteTokenServices()
        remoteTokenServices.setCheckTokenEndpointUrl("$authServerUrl/oauth/check_token")
        remoteTokenServices.setClientId(clientId)
        remoteTokenServices.setClientSecret(clientSecret)
        return remoteTokenServices
    }

    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.tokenServices(remoteTokenServices()).resourceId(resourceId).stateless(true)
    }

    override fun configure(http: HttpSecurity) {
        http.antMatcher("$BASE_API_URL/**")
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "$BASE_API_URL/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.POST, "$BASE_API_URL/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PUT, "$BASE_API_URL/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.DELETE, "$BASE_API_URL/**").access("#oauth2.hasScope('write')")
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }
}
