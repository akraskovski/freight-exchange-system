package com.github.akraskovski.fes.core.domain.service.notification

import com.github.akraskovski.fes.core.domain.model.UserInvite
import com.github.akraskovski.fes.email.notification.EmailService
import org.springframework.stereotype.Service

const val USER_INVITE_TEMPLATE_ID: Int = 574383

/**
 * Basic implementation of the NotificationService.
 */
@Service
class BasicNotificationService(private val emailService: EmailService) : NotificationService {

    override fun notifyUserInvite(userInvite: UserInvite) {
        val variables = mapOf(Pair("token", userInvite.token), Pair("company", userInvite.company.name))
        emailService.send(userInvite.email, USER_INVITE_TEMPLATE_ID, variables)
    }
}