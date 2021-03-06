package com.github.akraskovski.fes.core.domain.service

import com.github.akraskovski.fes.core.domain.model.common.AbstractEntity
import com.github.akraskovski.fes.core.domain.service.exception.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Basic implementation of the CommonService using.
 * Could be used as default delegated service.
 */
class BasicOperationService<T : AbstractEntity, R> constructor(private val jpaRepository: JpaRepository<T, R>) : CommonService<T, R> {

    override fun save(obj: T): T = jpaRepository.save(obj)

    override fun findById(id: R): T = jpaRepository.findById(id).orElseThrow { EntityNotFoundException() }

    override fun findAll(): List<T> = jpaRepository.findAll()

    override fun delete(id: R) = jpaRepository.deleteById(id)

    override fun delete(obj: T) = jpaRepository.delete(obj)
}