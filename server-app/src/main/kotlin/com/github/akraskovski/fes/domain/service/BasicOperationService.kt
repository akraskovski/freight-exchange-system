package com.github.akraskovski.fes.domain.service

import com.github.akraskovski.fes.domain.model.common.AbstractEntity
import com.github.akraskovski.fes.domain.service.exception.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Basic implementation of the CommonService using.
 * Could be used as default delegated service.
 */
class BasicOperationService<T : AbstractEntity, R> constructor(val jpaRepository: JpaRepository<T, R>) : CommonService<T, R> {

    override fun register(obj: T): T = jpaRepository.save(obj)

    override fun findById(id: R): T = jpaRepository.findById(id).orElseThrow { EntityNotFoundException() }

    override fun findAll(): List<T> = jpaRepository.findAll()

    override fun update(obj: T): T = jpaRepository.save(obj)

    override fun delete(id: R) = jpaRepository.deleteById(id)

    override fun delete(obj: T) = jpaRepository.delete(obj)
}