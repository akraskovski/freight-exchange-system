package com.github.akraskovski.auth.server.domain.service;

import com.github.akraskovski.auth.server.domain.model.User;
import com.github.akraskovski.auth.server.domain.repository.UserRepository;
import com.github.akraskovski.auth.server.domain.service.exception.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Basic user service.
 */
@Service
@RequiredArgsConstructor
public class BasicUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signUp(final User user) {
        if (findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(final String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Cannot find user by id: %s", id)));
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(final String id) {
        userRepository.deleteById(id);
    }
}
