package com.github.akraskovski.fes.core.domain.model;

/**
 * Application authorities, which can be applied to a {@link User}.
 */
enum class Authority {
    ROLE_ADMIN,
    ROLE_COMPANY_ADMIN,
    ROLE_CLIENT,
    ROLE_CARRIER,
    ROLE_FORWARDER
}
