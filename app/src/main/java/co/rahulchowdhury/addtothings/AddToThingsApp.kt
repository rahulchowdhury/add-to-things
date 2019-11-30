package co.rahulchowdhury.addtothings

import android.app.Application
import co.rahulchowdhury.addtothings.di.module.networkModule
import co.rahulchowdhury.addtothings.di.module.thingsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AddToThingsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AddToThingsApp)
            modules(listOf(networkModule, thingsModule))
        }
    }
}
