package com.github.akraskovski.auth.server.domain.repository;

import com.github.akraskovski.auth.server.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring data JPA {@link User} repository.
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * Searches {@link User} full-matches for a given username.
     *
     * @param email input param
     * @return found object or null if not exists
     */
    Optional<User> findByEmail(String email);
}
