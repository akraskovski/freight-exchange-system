package com.github.akraskovski.auth.server.web.security.config;

import com.github.akraskovski.auth.server.domain.model.Authority
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

const val RESOURCE_ID: String = "fes_api"
const val CLIENT_ID: String = "fes-client"
const val CLIENT_SECRET: String = "aH{G_{])6y[j8apFzqaRJ-BbwpAb{c?/4P-xC[,2yN<m94Y9"

val CLIENT_SCOPES: Array<String> = arrayOf("read", "write")

/**
 * Spring OAuth2.0 Authorization Server configuration. Using InMemory client details(could be configured to store
 * in preconfigured DB structure).
 * <p>
 * Token store is used with JDBC datasource, hence resource server could use stored in DB token
 * to authorize client(or could be configured to call this Auth server via Spring API: /token/authorize)
 */
@Configuration
@Import(ServerSecurityConfig::class)
@EnableAuthorizationServer
class AuthServerConfig @Autowired constructor(
        val authenticationManager: AuthenticationManager,
        val customUserDetailsService: UserDetailsService,
        val passwordEncoder: PasswordEncoder,
        val dataSource: DataSource) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun tokenStore() = JdbcTokenStore(dataSource)

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!.passwordEncoder(passwordEncoder).checkTokenAccess("permitAll()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .resourceIds(RESOURCE_ID)
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .authorities(Authority.ROLE_ADMIN.name)
                .scopes(*CLIENT_SCOPES)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .userDetailsService(customUserDetailsService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
    }
}
