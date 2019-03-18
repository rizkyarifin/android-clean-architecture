package sample.base.app.data.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import sample.base.app.data.model.NewsResponse

interface AppService {

    @GET("top-headlines")
    fun getTopHeadLines(@Query("country") country : String,
                        @Query("page") page : Int,
                        @Query("apiKey") apiKey : String) : Observable<NewsResponse>
}
