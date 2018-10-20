package com.github.akraskovski.fes.domain.repository.security

import com.github.akraskovski.fes.domain.repository.extension.getForEntityWithAuth
import com.github.akraskovski.fes.web.config.AuthServerProperties
import org.apache.tomcat.util.codec.binary.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

/**
 * Basic implementation of the AuthorizationRepository.
 */
@Service
class OAuthAuthorizationRepository @Autowired constructor(
    private val authProperties: AuthServerProperties,
    private val restTemplate: RestTemplate
) : AuthorizationRepository {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun isAccountRegistered(userId: String): Boolean {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId"

        return try {

            val encodePasswordFun = {
                "Basic ${String(Base64.encodeBase64("${authProperties.admin!!.email}:${authProperties.admin!!.password}".toByteArray()))}"
            }
            val response = restTemplate.getForEntityWithAuth<Map<String, String>>(requestUrl, encodePasswordFun)

            response.statusCode == HttpStatus.OK && response.body?.get("id") == userId

        } catch (e: RestClientException) {
            log.error("Couldn't execute fetching auth server user request", e)
            false
        }
    }
}