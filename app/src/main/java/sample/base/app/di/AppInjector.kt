package sample.base.app.di

import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import sample.base.app.data.network.repository.Repository
import sample.base.app.ui.main.MainViewModel
import sample.base.app.utils.rx.AppSchedulerProvider
import sample.base.app.utils.rx.SchedulerProvider

val appModule = module {

    viewModel { MainViewModel(get(), get()) }
    single { Repository(get()) }
    factory { AppSchedulerProvider() as SchedulerProvider }

}