package com.github.akraskovski.auth.server.web.controller;

import com.github.akraskovski.auth.server.domain.model.User;
import com.github.akraskovski.auth.server.domain.service.UserService;
import com.github.akraskovski.auth.server.web.controller.dto.IdDto;
import com.github.akraskovski.auth.server.web.controller.dto.SignUpUser;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Mapper mapper;

    /**
     * Sign up response entity.
     *
     * @param signUpUser the sign up user
     * @return the response entity
     */
    @PostMapping("/sign-up")
    public ResponseEntity<IdDto> signUp(@RequestBody @Valid final SignUpUser signUpUser) {
        final User registeredUser = userService.signUp(mapper.map(signUpUser, User.class));

        return ResponseEntity.ok(new IdDto(registeredUser.getId()));
    }

    /**
     * Me response entity.
     *
     * @return the response entity
     */
    @GetMapping("/me")
    public ResponseEntity<String> me() {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(principal.toString());
    }
}
