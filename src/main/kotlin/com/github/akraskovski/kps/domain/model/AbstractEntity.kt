package com.github.akraskovski.kps.domain.model

import org.springframework.data.annotation.Id

/**
 * Common domain entity contains unique identifier for documents.
 */
abstract class AbstractEntity(@Id val id: String?)