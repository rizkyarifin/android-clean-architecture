package sample.base.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sample.base.app.base.BaseViewModel
import sample.base.app.data.model.Article
import sample.base.app.data.network.repository.NewsRepository
import sample.base.app.utils.ext.with
import sample.base.app.utils.rx.SchedulerProvider

class MainViewModel(
    private val repo: NewsRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val dataNews = MutableLiveData<List<Article>>()

    val mDataNews: LiveData<List<Article>>
        get() = dataNews

    init {
        getNews()
    }

    fun getNews() {
        launch {
            isLoading.set(true)
            repo.getNews().with(scheduler).subscribe(
                {
                    isLoading.set(false)
                    dataNews.value = it.articles
                },
                { err ->
                    isLoading.set(false)
                    showMessage.value = handleError(err)
                })
        }
    }
}