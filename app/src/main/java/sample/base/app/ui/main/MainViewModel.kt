package sample.base.app.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import sample.base.app.base.BaseState
import sample.base.app.base.BaseViewModel
import sample.base.app.data.network.repository.Repository
import sample.base.app.utils.ext.with
import sample.base.app.utils.rx.SchedulerProvider

class MainViewModel(
    private val repo: Repository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val dataNews = MutableLiveData<BaseState>()

    val mDataNews : LiveData<BaseState>
        get() = dataNews

    init {
        getNews()
    }

    fun getNews() {
        launch {
            dataNews.value = BaseState.Loading
            repo.getNews().with(scheduler).subscribe(
                {
                    dataNews.value = BaseState.Data(it.articles)
                },
                { err ->
                    dataNews.value = BaseState.Error(handleError(err))
                    err.printStackTrace()
                })
        }
    }

}