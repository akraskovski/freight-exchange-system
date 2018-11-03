package com.github.akraskovski.fes.core.domain.repository.security

import com.github.akraskovski.fes.core.domain.model.AuthServerUser
import com.github.akraskovski.fes.core.domain.repository.extension.getForEntityWithAuth
import com.github.akraskovski.fes.core.domain.repository.extension.putForEntityWithAuth
import com.github.akraskovski.fes.web.config.AuthServerProperties
import org.apache.tomcat.util.codec.binary.Base64
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

/**
 * Basic implementation of the AuthorizationRepository.
 */
@Service
class BaseAuthorizationRepository @Autowired constructor(
    private val authProperties: AuthServerProperties,
    private val restTemplate: RestTemplate
) : AuthorizationRepository {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getById(authServerId: String): AuthServerUser? {
        return getUserById(authServerId).let {
            when (it.statusCode) {
                HttpStatus.OK -> it.body
                HttpStatus.NOT_FOUND -> null
                else -> throw UnsupportedOperationException("Unexpected response code: ${it.statusCode}")
            }
        }
    }

    override fun isAccountRegistered(userId: String): Boolean {
        return try {
            return getUserById(userId).let { it.statusCode == HttpStatus.OK && it.body?.id == userId }
        } catch (e: RestClientException) {
            log.error("Couldn't execute fetching auth server user request", e)
            false
        }
    }

    override fun activateAccount(userId: String) {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId/activate"

        try {
            restTemplate.putForEntityWithAuth<Map<String, String>>(requestUrl) { "Basic ${String(encodeBasic())}" }
                .let { if (it.statusCode != HttpStatus.OK) throw RestClientException("Unexpected response status: ${it.statusCode}") }
        } catch (e: RestClientException) {
            log.error("Couldn't execute user activation request", e)
        }
    }

    private fun getUserById(userId: String): ResponseEntity<AuthServerUser> {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId"
        return restTemplate.getForEntityWithAuth(requestUrl) { "Basic ${String(encodeBasic())}" }
    }

    private fun encodeBasic() = Base64.encodeBase64("${authProperties.admin!!.email}:${authProperties.admin!!.password}".toByteArray())
}