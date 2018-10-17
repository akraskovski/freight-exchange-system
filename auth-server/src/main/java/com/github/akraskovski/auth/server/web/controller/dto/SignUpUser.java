package com.github.akraskovski.auth.server.web.controller.dto;

import com.github.akraskovski.auth.server.domain.model.Authority;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Data transfer object for registering new user.
 */
@Getter
@Setter
public class SignUpUser {

    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 8)
    private String password;
    @NotNull
    private Set<Authority> authorities;
}
