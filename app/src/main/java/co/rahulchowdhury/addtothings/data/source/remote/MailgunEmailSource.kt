package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.BuildConfig

class MailgunEmailSource(
    private val mailgunApiService: MailgunApiService
) : ThingsEmailSource {
    override suspend fun emailToThings(
        task: String,
        note: String
    ) {
        val from = BuildConfig.EMAIL_FROM_ADDRESS
        val to = BuildConfig.EMAIL_TO_ADDRESS

        return mailgunApiService.sendEmail(
            from = from,
            to = to,
            subject = task,
            message = note
        )
    }
}
