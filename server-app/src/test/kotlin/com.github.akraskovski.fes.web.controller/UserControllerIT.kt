package com.github.akraskovski.fes.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.akraskovski.fes.core.domain.model.common.Gender
import com.github.akraskovski.fes.web.controller.config.ControllerTestRunner
import com.github.akraskovski.fes.web.dto.IdDto
import com.github.akraskovski.fes.web.dto.user.SignUpUser
import org.hamcrest.Matchers.isEmptyOrNullString
import org.hamcrest.Matchers.not
import org.junit.Assert.assertThat
import org.junit.Ignore
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * UserController integration tests.
 */
@Ignore
class UserControllerIT : ControllerTestRunner() {

    val userUrl = "/api/v1/user"

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun registerAccount_whenValidDetails_thenSuccess() {
        mvc.apply { springSecurity() }
        val signUpUser = generateTestUser(RandomValueStringGenerator()::generate)

        val stringResponse = mvc.perform {
            post("$userUrl/account/register")
                    .with(user("admin").roles("ADMIN"))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsString(signUpUser)).buildRequest(it)
        }.andExpect { status().isOk }.andReturn().response.contentAsString

        val idDto = mapper.readValue<IdDto>(stringResponse)
        assertThat(idDto.id, not(isEmptyOrNullString()))
    }
}

fun generateTestUser(randomString: () -> String) = SignUpUser(randomString(), "testFirstName", "testLastName", Gender.MALE, "integrationTest@email.com", "102")