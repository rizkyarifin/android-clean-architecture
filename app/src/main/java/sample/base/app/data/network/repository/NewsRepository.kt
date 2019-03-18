package sample.base.app.data.network.repository

import io.reactivex.Observable
import sample.base.app.data.model.NewsResponse
import sample.base.app.data.network.AppService
import sample.base.app.utils.Network

interface NewsRepository {
    fun getNews() : Observable<NewsResponse>
}

class NewsRepositoryImpl (private val appService: AppService) : NewsRepository {

    override fun getNews() : Observable<NewsResponse> =
        appService.getTopHeadLines("us", 1, Network.API_KEY)
}