package co.rahulchowdhury.addtothings.di.module

import co.rahulchowdhury.addtothings.data.repo.DefaultThingsRepository
import co.rahulchowdhury.addtothings.data.repo.ThingsRepository
import co.rahulchowdhury.addtothings.data.source.remote.MailgunApiService
import co.rahulchowdhury.addtothings.data.source.remote.MailgunEmailSource
import co.rahulchowdhury.addtothings.data.source.remote.ThingsEmailSource
import co.rahulchowdhury.addtothings.ui.AddTodoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val thingsModule = module {
    single { provideThingsEmailSource(get()) }
    single { provideThingsRepository(get()) }
    viewModel { provideAddTodoViewModel(get()) }
}

fun provideThingsEmailSource(
    mailgunApiService: MailgunApiService
): ThingsEmailSource = MailgunEmailSource(mailgunApiService)

fun provideThingsRepository(
    thingsEmailSource: ThingsEmailSource
): ThingsRepository = DefaultThingsRepository(thingsEmailSource)

fun provideAddTodoViewModel(
    thingsRepository: ThingsRepository
): AddTodoViewModel = AddTodoViewModel(thingsRepository)
