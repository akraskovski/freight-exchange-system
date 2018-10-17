package com.github.akraskovski.auth.server.web.security.config;

import com.github.akraskovski.auth.server.domain.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Spring OAuth2.0 Authorization Server configuration. Using InMemory client details(could be configured to store
 * in preconfigured DB structure).
 * <p>
 * Token store is used with JDBC datasource, hence resource server could use stored in DB token
 * to authorize client(or could be configured to call this Auth server via Spring API: /token/authorize)
 */
@Configuration
@EnableAuthorizationServer
@Import(ServerSecurityConfig.class)
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "company_api";
    private static final String CLIENT_ID = "curl-client";
    private static final String CLIENT_SECRET = "your-client-secret";
    private static final String[] CLIENT_SCOPES = {"read", "write"};

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    @Autowired
    public AuthServerConfig(final AuthenticationManager authenticationManager,
                            final UserDetailsService customUserDetailsService,
                            final DataSource dataSource,
                            final PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder).checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_ID)
                .secret(passwordEncoder.encode(CLIENT_SECRET))
                .resourceIds(RESOURCE_ID)
                .authorizedGrantTypes("password", "refresh_token")
                .authorities(Authority.ROLE_ADMIN.name(), Authority.ROLE_USER.name())
                .scopes(CLIENT_SCOPES);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .userDetailsService(customUserDetailsService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }
}
