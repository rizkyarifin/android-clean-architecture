package sample.base.app.data.network.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import sample.base.app.data.model.NewsResponse
import sample.base.app.data.network.AppService

class Repository (private val appService: AppService) {

    fun getNews() : Observable<NewsResponse> =
        appService.getTopHeadLines("en", 1)
}