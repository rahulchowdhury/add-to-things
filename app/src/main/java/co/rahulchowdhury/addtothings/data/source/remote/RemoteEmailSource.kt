package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.data.model.remote.MailgunResponse

interface RemoteEmailSource {
    suspend fun sendEmail(
        to: String,
        subject: String,
        message: String
    ): MailgunResponse
}
