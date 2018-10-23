package com.github.akraskovski.fes.web.controller.config

import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

/**
 * Common controllers integration testing configuration.
 */
@SpringBootTest
@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@ActiveProfiles(value = ["dev", "test"])
abstract class ControllerTestRunner