package com.github.akraskovski.auth.server.web.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link org.dozer.DozerBeanMapper} configuration.
 */
@Configuration
public class DozerConfig {

    /**
     * Configures dozer mapper.
     *
     * @return the dozer bean mapper
     */
    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
