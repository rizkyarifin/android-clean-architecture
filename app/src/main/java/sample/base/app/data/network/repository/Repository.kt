package sample.base.app.data.network.repository

import io.reactivex.Observable
import io.reactivex.Single
import sample.base.app.data.model.NewsResponse
import sample.base.app.data.network.AppService
import sample.base.app.utils.ext.mapNetworkErrors

class Repository (private val appService: AppService) {

    fun getNews() : Single<NewsResponse> =
        appService.getTopHeadLines("us", 1).mapNetworkErrors()
}