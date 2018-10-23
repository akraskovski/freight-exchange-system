package com.github.akraskovski.fes.core.domain.service

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity

/**
 * Common CRUD service, showing work of generics in kotlin.
 */
interface CommonService<T : AbstractEntity, in R> {

    /**
     * Common create/update operation.
     */
    fun save(obj: T): T

    /**
     * Find domain entity by ID or throws EntityNotFoundException.
     */
    fun findById(id: R): T

    /**
     * Returns all entities with a given type.
     */
    fun findAll(): List<T>

    /**
     * Removes entity by a given id.
     */
    fun delete(id: R)

    /**
     * Removes given at parameter entity.
     */
    fun delete(obj: T)
}