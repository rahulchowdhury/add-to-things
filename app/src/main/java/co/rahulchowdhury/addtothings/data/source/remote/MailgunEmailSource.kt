package co.rahulchowdhury.addtothings.data.source.remote

import co.rahulchowdhury.addtothings.BuildConfig
import co.rahulchowdhury.addtothings.data.model.Todo

class MailgunEmailSource(
    private val mailgunApiService: MailgunApiService
) : ThingsEmailSource {
    override suspend fun emailToThings(
        todo: Todo
    ) {
        val from = BuildConfig.EMAIL_FROM_ADDRESS
        val to = BuildConfig.EMAIL_TO_ADDRESS

        return mailgunApiService.sendEmail(
            from = from,
            to = to,
            subject = todo.task,
            message = todo.note
        )
    }
}
