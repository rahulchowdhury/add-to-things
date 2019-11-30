package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.BuildConfig
import co.rahulchowdhury.addtothings.data.model.remote.MailgunResponse

class MailgunEmailSource(
    private val mailgunApiService: MailgunApiService
) : RemoteEmailSource {
    override suspend fun sendEmail(
        to: String,
        subject: String,
        message: String
    ): MailgunResponse {
        val from = BuildConfig.EMAIL_FROM_ADDRESS

        return mailgunApiService.sendEmail(
            from = from,
            to = to,
            subject = subject,
            message = message
        )
    }
}
