package com.github.akraskovski.kps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPostServiceApplication

fun main(args: Array<String>) {
    runApplication<KotlinPostServiceApplication>(*args)
}
