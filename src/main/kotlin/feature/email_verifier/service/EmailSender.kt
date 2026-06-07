package com.shayan.feature.email_verifier.service

import com.resend.Resend
import com.resend.services.emails.model.CreateEmailOptions
import com.shayan.feature.sender.constants.EmailVerifierConst
import core.consts.ENV
import core.util.EnvLoader
import feature.email_verifier.template.VerificationEmailTemplate
import org.koin.core.component.getScopeId

class EmailSender(
    private val resend: Resend = Resend(EnvLoader.get(ENV.EMAIL_SENDER_API_KEY))
) {

    private fun generateParams(
        code: String,
        email: String,
        name: String
    ): CreateEmailOptions {
        val html = VerificationEmailTemplate.create(
            username = name,
            code = code
        )

        return CreateEmailOptions.builder()
            .from(EmailVerifierConst.FROM)
            .to(email)
            .subject(EmailVerifierConst.SUBJECT)
            .html(html)
            .build()
    }

    fun send(
        to: String,
        name: String,
        code: String
    ):Boolean =
         resend
            .emails()
            .send(
                generateParams(
                    code = code,
                    email = to,
                    name = name
                )
            ) != null


}