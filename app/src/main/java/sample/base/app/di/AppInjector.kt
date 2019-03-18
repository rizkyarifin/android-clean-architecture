package sample.base.app.di

import android.app.Application
import android.content.Context
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import sample.base.app.data.network.repository.NewsRepository
import sample.base.app.data.network.repository.NewsRepositoryImpl
import sample.base.app.ui.main.MainViewModel
import sample.base.app.utils.rx.AppSchedulerProvider
import sample.base.app.utils.rx.SchedulerProvider

val appModule = module {
    viewModel { MainViewModel(get(), get()) }

    single { NewsRepositoryImpl(get()) as NewsRepository }

    factory { AppSchedulerProvider() as SchedulerProvider }
}
