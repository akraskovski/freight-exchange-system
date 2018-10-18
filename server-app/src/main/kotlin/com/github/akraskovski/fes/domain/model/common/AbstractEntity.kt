package com.github.akraskovski.fes.domain.model.common

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.Id
import javax.persistence.GeneratedValue
import javax.persistence.MappedSuperclass

/**
 * Common domain entity contains unique identifier for documents.
 */
@MappedSuperclass
abstract class AbstractEntity(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        val id: String?
)