package sample.base.app

import android.app.Application
import org.koin.android.ext.android.startKoin
import sample.base.app.di.appModule
import sample.base.app.di.module.networkModule

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, networkModule))
    }
}