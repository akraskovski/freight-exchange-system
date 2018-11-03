package com.github.akraskovski.fes.core.domain.repository.exception

/**
 *Common exception for the repository layer.
 */
class RepositoryException : RuntimeException {

    constructor(message: String? = "Repository exception was occurred") : super(message)

    constructor(message: String?, e: Throwable) : super(message, e)
}