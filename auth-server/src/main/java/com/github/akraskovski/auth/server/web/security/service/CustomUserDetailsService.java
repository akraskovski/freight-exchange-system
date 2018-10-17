package com.github.akraskovski.auth.server.web.security.service;

import com.github.akraskovski.auth.server.domain.model.Authority;
import com.github.akraskovski.auth.server.domain.model.User;
import com.github.akraskovski.auth.server.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the Spring Security {@link UserDetailsService}.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("Cannot find user with email: %s", email)));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), convertAuthorities(user.getAuthorities()));
    }

    private List<GrantedAuthority> convertAuthorities(final Set<Authority> userAuthorities) {
        return userAuthorities.stream()
                .map(Authority::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
