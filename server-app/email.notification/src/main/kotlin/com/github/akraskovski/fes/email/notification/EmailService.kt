package com.github.akraskovski.fes.email.notification

/**
 * Common email notification process service.
 */
interface EmailService {

    /**
     * Sends email to the given receiver email with a predefined template.
     */
    fun send(receiver: String, templateId: Int, variables: Map<String, String>)
}