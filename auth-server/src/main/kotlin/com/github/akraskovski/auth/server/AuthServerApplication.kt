package com.github.akraskovski.auth.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Spring Boot bootstrap class.
 */
@SpringBootApplication
class AuthServerApplication

fun main(args: Array<String>) {
    runApplication<AuthServerApplication>(*args)
}