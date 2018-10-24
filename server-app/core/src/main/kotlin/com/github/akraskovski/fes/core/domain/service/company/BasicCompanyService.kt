package com.github.akraskovski.fes.core.domain.service.company

import com.github.akraskovski.fes.core.domain.model.Company
import com.github.akraskovski.fes.core.domain.model.UserInvite
import com.github.akraskovski.fes.core.domain.repository.company.CompanyRepository
import com.github.akraskovski.fes.core.domain.repository.company.UserInviteRepository
import com.github.akraskovski.fes.core.domain.service.BasicOperationService
import com.github.akraskovski.fes.core.domain.service.CommonService
import com.github.akraskovski.fes.core.domain.service.extension.SecurityHelper
import com.github.akraskovski.fes.core.domain.service.notification.NotificationService
import com.github.akraskovski.fes.core.domain.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

/**
 * Basic implementation of the CompanyService.
 */
@Service
class BasicCompanyService @Autowired constructor(
    private val userInviteRepository: UserInviteRepository,
    private val companyRepository: CompanyRepository,
    private val securityHelper: SecurityHelper,
    private val notificationService: NotificationService,
    private val userService: UserService
) : CommonService<Company, String> by BasicOperationService(companyRepository), CompanyService {

    override fun create(company: Company, ownerId: String) = save(company.apply { isActive = false; owner = userService.findById(ownerId) })

    override fun sendInvite(email: String) {
        val currentUser = securityHelper.getCurrentUser()
        val company = companyRepository.findByOwner(currentUser)
            ?: throw IllegalArgumentException("Current user is not admin of the company")

        val userInvite: UserInvite = userInviteRepository.findByEmailAndCompany(email, company)?.let(::processExistingInvite)
            ?: processNewInvite(email, company)

        notificationService.notifyUserInvite(userInvite)
    }

    private fun processExistingInvite(userInvite: UserInvite): UserInvite {
        val currentTime = LocalDateTime.now()

        if (userInvite.expiresAt.isAfter(currentTime)) {
            userInvite.expiresAt = currentTime.plusDays(1)
            userInviteRepository.save(userInvite)
        }

        return userInvite
    }

    private fun processNewInvite(email: String, company: Company): UserInvite {
        val expireDate = LocalDateTime.now().plusDays(1)
        val token = UUID.randomUUID().toString()

        return userInviteRepository.save(UserInvite(null, company, email, token, expireDate))
    }
}