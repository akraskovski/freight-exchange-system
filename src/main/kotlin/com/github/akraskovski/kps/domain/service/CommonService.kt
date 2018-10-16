package com.github.akraskovski.kps.domain.service

import com.github.akraskovski.kps.domain.model.AbstractEntity

/**
 * Common CRUD service, showing work of generics in kotlin.
 */
interface CommonService<T : AbstractEntity, in R> {

    fun create(obj: T): T

    fun findById(id: R): T

    fun findAll(): List<T>

    fun update(obj: T): T

    fun delete(id: R)

    fun delete(obj: T)
}