package com.github.akraskovski.auth.server.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.akraskovski.auth.server.domain.model.Authority;
import com.github.akraskovski.auth.server.domain.service.UserService;
import com.github.akraskovski.auth.server.web.controller.dto.IdDto;
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    private static final String TEST_EMAIL = "test_user@gmail.com";
    private static final String USER_URL = "/user";

    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @Transactional
    public void signUp_whenValidCredentials_thenSuccess() throws Exception {

        final String password = RandomStringUtils.randomAlphabetic(20);

        final SignUpUser signUpUser = new SignUpUser();
        signUpUser.setEmail(TEST_EMAIL);
        signUpUser.setPassword(password);
        signUpUser.setAuthorities(Collections.singleton(Authority.ROLE_USER));

        final String stringResponse = mvc.perform(post(USER_URL + "/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUpUser)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        final IdDto idDto = mapper.readValue(stringResponse, IdDto.class);

        Assert.assertThat(userService.getById(idDto.getId()), Matchers.notNullValue());
    }


    @Test
    public void me_whenNotAuthenticated_thenUnauthorized() throws Exception {
        mvc.perform(get("/me"))
                .andExpect(status().isUnauthorized());
    }
}