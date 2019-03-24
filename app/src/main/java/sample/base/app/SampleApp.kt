package sample.base.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sample.base.app.di.appModule
import sample.base.app.di.module.networkModule

class SampleApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SampleApp)
            modules(listOf(appModule, networkModule))
        }
    }
}