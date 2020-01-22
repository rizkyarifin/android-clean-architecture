package sample.base.app.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sample.base.app.data.model.NewsResponse

interface AppService {

    @GET("everything?q=sports&apiKey=aa67d8d98c8e4ad1b4f16dbd5f3be348")
    fun getNews(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Single<NewsResponse>
}
