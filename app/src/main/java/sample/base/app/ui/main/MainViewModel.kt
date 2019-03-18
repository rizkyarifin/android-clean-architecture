package sample.base.app.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import sample.base.app.base.BaseViewModel
import sample.base.app.base.Resource
import sample.base.app.base.ResourceState
import sample.base.app.data.model.Article
import sample.base.app.data.network.repository.NewsRepository
import sample.base.app.utils.ext.with
import sample.base.app.utils.rx.SchedulerProvider

class MainViewModel(
    private val repo: NewsRepository,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val dataNews = MutableLiveData<Resource<List<Article>>>()

    val mDataNews: LiveData<Resource<List<Article>>>
        get() = dataNews

    init {
        getNews()
    }

    fun getNews() {
        launch {
            dataNews.setLoading()
            repo.getNews().with(scheduler).subscribe(
                {
                    dataNews.setSuccess(it.articles)
                },
                { err ->
                    dataNews.setError(handleError(err))
                    err.printStackTrace()
                })
        }
    }
}

fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, message))