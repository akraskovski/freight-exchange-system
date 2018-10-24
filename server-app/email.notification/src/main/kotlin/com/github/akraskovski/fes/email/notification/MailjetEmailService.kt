package com.github.akraskovski.fes.email.notification

import com.mailjet.client.ClientOptions
import com.mailjet.client.MailjetClient
import com.mailjet.client.MailjetRequest
import com.mailjet.client.resource.Emailv31
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


/**
 * Mailjet API email sender service implementation.
 */
@Service
class MailjetEmailService : EmailService {

    @Value("\${email.notification.sender.address}")
    lateinit var senderEmail: String

    @Value("\${email.notification.sender.name:Freight Exchange System Support}")
    lateinit var senderName: String

    @Value("\${email.notification.key.public}")
    lateinit var publicKey: String

    @Value("\${email.notification.key.private}")
    lateinit var privateKey: String

    private val client: MailjetClient by lazy { MailjetClient(publicKey, privateKey, ClientOptions("v3.1")) }
    private val senderInfo by lazy { JSONObject().put(Emailv31.Message.EMAIL, senderEmail).put(Emailv31.Message.NAME, senderName) }

    override fun send(receiver: String, templateId: Int, variables: Map<String, String>) {
        //TODO: add exception handling.
        val message =
            JSONObject().put(Emailv31.Message.FROM, senderInfo)
                .put(Emailv31.Message.TO, this createReceiverInfo receiver)
                .put(Emailv31.Message.TEMPLATEID, templateId)
                .put(Emailv31.Message.TEMPLATELANGUAGE, true)
                .put(Emailv31.Message.VARIABLES, JSONObject(variables))

        client.post(MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, JSONArray().put(message)))
    }

    private infix fun createReceiverInfo(receiverEmail: String) = JSONArray().put(JSONObject().put(Emailv31.Message.EMAIL, receiverEmail))
}