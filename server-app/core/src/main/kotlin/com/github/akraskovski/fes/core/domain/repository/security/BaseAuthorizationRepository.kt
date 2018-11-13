package com.github.akraskovski.fes.core.domain.repository.security

import com.github.akraskovski.fes.core.domain.model.AuthServerUser
import com.github.akraskovski.fes.core.domain.repository.exception.RepositoryException
import com.github.akraskovski.fes.core.domain.repository.extension.getForEntityWithAuth
import com.github.akraskovski.fes.core.domain.repository.extension.putForEntityWithAuth
import com.github.akraskovski.fes.web.config.AuthServerProperties
import org.apache.tomcat.util.codec.binary.Base64
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

    override fun findById(authServerId: String): AuthServerUser? {
        return extractAuthUser(getUserById(authServerId))
    }

    override fun findByEmail(email: String): AuthServerUser? {
        val requestUrl = "${authProperties.connection!!.url}/user/email/$email"

        return try {
            extractAuthUser(restTemplate.getForEntityWithAuth(requestUrl) { "Basic ${String(encodeBasic())}" })
        } catch (e: RestClientException) {
            throw RepositoryException("Couldn't execute find by email request", e)
        }
    }

    override fun activateAccount(userId: String) {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId/activate"

        try {
            restTemplate.putForEntityWithAuth<Map<String, String>>(requestUrl) { "Basic ${String(encodeBasic())}" }
                .let { if (it.statusCode != HttpStatus.OK) throw RestClientException("Unexpected response status: ${it.statusCode}") }
        } catch (e: RestClientException) {
            throw RepositoryException("Couldn't execute user activation request", e)
        }
    }

    private fun extractAuthUser(response: ResponseEntity<AuthServerUser>) = when (response.statusCode) {
        HttpStatus.OK -> response.body
        HttpStatus.NOT_FOUND -> null
        else -> throw UnsupportedOperationException("Unexpected response code: ${response.statusCode}")
    }

    private fun getUserById(userId: String): ResponseEntity<AuthServerUser> {
        val requestUrl = "${authProperties.connection!!.url}/user/$userId"

        return try {
            restTemplate.getForEntityWithAuth(requestUrl) { "Basic ${String(encodeBasic())}" }
        } catch (e: RestClientException) {
            throw RepositoryException("Couldn't execute get user by id request", e)
        }
    }

    private fun encodeBasic() = Base64.encodeBase64("${authProperties.admin!!.email}:${authProperties.admin!!.password}".toByteArray())
}