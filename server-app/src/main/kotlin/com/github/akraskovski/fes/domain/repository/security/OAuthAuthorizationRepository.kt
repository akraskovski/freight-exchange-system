package com.github.akraskovski.fes.domain.repository.security

import com.github.akraskovski.fes.web.config.AuthServerProperties
import org.apache.tomcat.util.codec.binary.Base64
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Basic implementation of the AuthorizationRepository.
 */
@Service
class OAuthAuthorizationRepository @Autowired constructor(
    private val authProperties: AuthServerProperties,
    private val restTemplate: RestTemplate
) : AuthorizationRepository {

    override fun isAccountRegistered(userId: String): Boolean {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId"

        //TODO: implement
//        restTemplate.getForEntity<Map<String, String>>(requestUrl)
        return false
    }

    private fun prepareAuthHeader(): String {
        val credentials = "${authProperties.admin!!.email}:${authProperties.admin!!.password}"
        val encodeBase64 = Base64.encodeBase64(credentials.toByteArray())
        return "Basic $encodeBase64"
    }
}