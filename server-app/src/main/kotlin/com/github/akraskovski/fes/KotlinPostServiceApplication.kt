package com.github.akraskovski.fes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Bootstrap class.
 */
@SpringBootApplication
class KotlinPostServiceApplication

fun main(args: Array<String>) {
    runApplication<KotlinPostServiceApplication>(*args)
}
