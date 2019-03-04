package sample.base.app.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import sample.base.app.model.NewsResponse

interface AppService {
    @GET("top-headlines")

    fun getTopHeadLines(@Query("country") country : String,
                        @Query("page") page : Int) : Deferred<NewsResponse>
}