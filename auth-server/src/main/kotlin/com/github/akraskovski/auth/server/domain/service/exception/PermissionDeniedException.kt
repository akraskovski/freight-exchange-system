package com.github.akraskovski.auth.server.domain.service.exception

/**
 * Custom permission denied exception for some operations forbiddance.
 */
class PermissionDeniedException(message: String? = "You don't have permissions to this operation") : RuntimeException(message)