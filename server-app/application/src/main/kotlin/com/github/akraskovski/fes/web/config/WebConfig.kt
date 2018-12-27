package com.github.akraskovski.fes.web.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * General web configuration spring bean.
 */
@Configuration
class WebConfig {

    /**
     * Creates configured rest template bean.
     *
     * @param objectMapper object mapper
     * @return created rest template
     */
    @Bean
    fun restTemplate(objectMapper: ObjectMapper): RestTemplate {
        val restTemplate = RestTemplate()

        val messageConverter = MappingJackson2HttpMessageConverter()
        messageConverter.setPrettyPrint(false)
        messageConverter.objectMapper = objectMapper

        restTemplate.messageConverters.removeIf { converter -> converter is MappingJackson2HttpMessageConverter }
        restTemplate.messageConverters.add(messageConverter)

        return restTemplate
    }

    @Bean
    fun corsFilter(): FilterRegistrationBean<*> {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.allowedOrigins = listOf("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)

        val bean = FilterRegistrationBean(CorsFilter(source))
        bean.order = Ordered.HIGHEST_PRECEDENCE

        return bean
    }
}