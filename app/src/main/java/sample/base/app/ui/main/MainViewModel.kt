package sample.base.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import sample.base.app.base.BaseViewModel
import sample.base.app.data.model.Article
import sample.base.app.data.network.AppService
import sample.base.app.data.network.repository.NewsDataSource
import sample.base.app.data.network.repository.NewsRepository
import sample.base.app.utils.ext.with
import sample.base.app.utils.rx.SchedulerProvider
import sample.base.app.utils.view.State

class MainViewModel(
    private val appService: AppService,
    private val scheduler: SchedulerProvider
) : BaseViewModel() {

    private val pageSize = 5
    var dataNews :LiveData<PagedList<Article>>
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        dataNews  = initializedPagedListBuilder(config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<NewsDataSource,
            State>(newsDataSourceLiveData, NewsDataSource::state)

    fun retry() {
        newsDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return dataNews.value?.isEmpty() ?: true
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Article> {

        val dataSourceFactory = object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                val newsDataSource = NewsDataSource(appService,mCompositeDisposable, scheduler)
                newsDataSourceLiveData.postValue(newsDataSource)
                return newsDataSource
            }
        }
        return LivePagedListBuilder<Int, Article>(dataSourceFactory, config)
    }
}