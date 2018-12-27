package com.github.akraskovski.fes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Bootstrap class.
 */
@SpringBootApplication(scanBasePackages = ["com.github.akraskovski.fes"])
class FreightExchangeSystemApplication

fun main(args: Array<String>) {
    runApplication<FreightExchangeSystemApplication>(*args)
}
