package com.github.akraskovski.fes.core.domain.service.notification

import com.github.akraskovski.fes.core.domain.model.UserInvite

/**
 * General notification service.
 */
interface NotificationService {

    /**
     * Notifies user was invited to register client account in a company.
     */
    fun notifyUserInvite(userInvite: UserInvite)
}